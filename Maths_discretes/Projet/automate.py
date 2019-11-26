# -*- coding: utf-8 -*-
from transition import *
from state import *
import os
import copy
from sp import *
from parser import *
from itertools import product
from automateBase import AutomateBase



class Automate(AutomateBase):

    def succElem(self, state, lettre):
        """State x str -> list[State]
        rend la liste des états accessibles à partir d'un état
        state par l'étiquette lettre
        """
        successeurs = []
        # t: Transitions
        for t in self.getListTransitionsFrom(state):
            if t.etiquette == lettre and t.stateDest not in successeurs:
                successeurs.append(t.stateDest)
        return successeurs


    def succ (self, listStates, lettre):
        """list[State] x str -> list[State]
        rend la liste des états accessibles à partir de la liste d'états
        listStates par l'étiquette lettre
        """
        successeurs = []
        #s: State
        for i in listStates:
            #t: Transition
            for t in self.getListTransitionsFrom(i):
                if t.etiquette == lettre and t.stateDest not in successeurs:
                    successeurs.append(t.stateDest)

        return successeurs




    """ Définition d'une fonction déterminant si un mot est accepté par un automate.
    Exemple :
            a=Automate.creationAutomate("monAutomate.txt")
            if Automate.accepte(a,"abc"):
                print "L'automate accepte le mot abc"
            else:
                print "L'automate n'accepte pas le mot abc"
    """
    @staticmethod
    def accepte(auto,mot) :
        """ Automate x str -> bool
        rend True si auto accepte mot, False sinon
        """
        #list_init: list[States]
        liste= auto.getListInitialStates()
        #c : str
        for c in mot:
            liste=auto.succ(liste,c)

        return State.isFinalIn(liste)


    @staticmethod
    def estComplet(auto,alphabet) :
        """ Automate x str -> bool
         rend True si auto est complet pour alphabet, False sinon
        """
        #liste_state : list[State]
        liste_state = auto.listStates
        #state : State
        for state in liste_state:
            #lettre : str
            for lettre in alphabet:
                if auto.succElem(state,lettre)==[]:
                    return False
        return True



    @staticmethod
    def estDeterministe(auto) :
        """ Automate  -> bool
        rend True si auto est déterministe, False sinon
        """
        #alphabet : str
        alphabet = auto.getAlphabetFromTransitions()
        #liste_state : list[State]
        liste_state = auto.listStates
        #state : State
        for state in liste_state:
            #lettre : str
            for lettre in alphabet:
                if len(auto.succElem(state,lettre))>1:
                    return False
        return True



    @staticmethod
    def completeAutomate(auto,alphabet) :
        """ Automate x str -> Automate
        rend l'automate complété d'auto, par rapport à alphabet
        """
        #newAuto : Automate
        newAuto = copy(auto)
        if(estComplet(newAuto,alphabet)):
            return newAuto
        #trash : State
        trash = State(len(auto.listStates)+1,False,False,"poubelle")
        newAuto.addState(trash)
        #liste_state : list[State]
        liste_state = newAuto.listStates
        #state : State
        for state in liste_state:
            #lettre : str
            for lettre in alphabet:
                if newAuto.succElem(state,lettre)==[]:
                    newAuto.addTransition(Transition(lettre,state,trash))

        return newAuto



    @staticmethod
    def determinisation(newAuto) :
        """ Automate  -> Automate
        rend l'automate déterminisé d'auto
        """
        '''#newAuto : Automate
        newAuto = copy(auto)
        if(estDeterministe(newAuto)):
            return newAuto'''
        #alphabet : str
        alphabet = newAuto.getAlphabetFromTransitions()
        #Liste : list(list[States])
        Liste=[newAuto.getListInitialStates()]
        #Liste_f : list[States]
        Liste_f=newAuto.getListFinalStates()
        Liste_temp=[]
        #Liste_Transition: liste[Transition]
        Liste_Transition=[]
        #new_States : set(State)
        new_States =set()
        #L2: list(list[State])
        L2=[]
        #i : int
        i=0
        k=0
        #s : str
        s=""
        #test : bool
        test=False
        #State_temp1 : State
        State_temp1=State(0,False,False)
        #State_temp3 : State
        State_temp2=State(0,False,False)
        while(Liste!=[]):
            print(Liste[0])
            """print(Liste_temp)
            print (L2)
            print (Liste)
            print (new_States)
            print (" ")"""
            if Liste[0] in L2:
                #print("a")
                s=str(Liste[0])
                for l in new_States:
                    if s==l.label:
                        State_temp1=l
                        break
            else:
               # print("b")
                L2.append(Liste[0])
                for k in Liste[0]:
                   if k in Liste_f:
                     test=True
                s=str(Liste[0])
                if i==0:
                   # print("n")
                    State_temp1=State(i,True,test,s)
                    i+=1
                else:
                    #print("p")
                    State_temp1=State(i,False,test,s)
                    i=i+1
                    test=False
                new_States.add(State_temp1)

            for lettre in alphabet:
                #print('lettre='+lettre)
                Liste_temp=newAuto.succ(Liste[0],lettre)
                #print(Liste_temp)
                if Liste_temp in L2:
                    #print("d")
                    s=str(Liste_temp)
                    for l in new_States:
                        if s==l.label:
                           # print("g")
                            State_temp2=l
                            Liste_Transition.append(Transition(State_temp1,lettre,State_temp2))
                            break
                else:
                   # print("e")
                    if Liste_temp!=[]:
                        L2.append(Liste_temp)
                        for k in Liste_temp:
                            if k in Liste_f:
                                test=True
                        s=str(Liste_temp)
                        State_temp2=State(i,False,test,s)
                        i=i+1
                        test=False
                        Liste_Transition.append(Transition(State_temp1,lettre,State_temp2))
                        #print(new_States)
                       # print('id='+str(State_temp2.id))

                        new_States.add(State_temp2)
                        #print(new_States)
                        Liste.append(Liste_temp)
                #for w in new_States:
                    #print('id='+str(w.id))
            del Liste[0]

        return AutomateBase(Liste_Transition)



    @staticmethod
    def complementaire(auto,alphabet):
        """ Automate -> Automate
        rend  l'automate acceptant pour langage le complémentaire du langage de a
        """
        return


    @staticmethod
    def intersection (auto0, auto1):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage l'intersection des langages des deux automates
        """
        return

    @staticmethod
    def union (auto0, auto1):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage l'union des langages des deux automates
        """
        return





    @staticmethod
    def concatenation (auto1, auto2):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage la concaténation des langages des deux automates
        """
        return


    @staticmethod
    def etoile (auto):
        """ Automate  -> Automate
        rend l'automate acceptant pour langage l'étoile du langage de a
        """
        return


automate=Automate.creationAutomate("exempleAutomate.txt")
print(automate)
"""print (automate.listStates)
s=State(6,False,True,"11")
print(s.__repr__())
print(automate.addState(s))
d=(6,False,True)
print(automate.addState(s))
print(automate)
print(s)
L=automate.listStates

s=""
for l in L:
    s=s+"_"+l.label
print(s)

print ((automate.listStates)[3].insertPrefix(6,"wesh alors"))
print(automate)
"""

a=Automate.determinisation(automate)
Liste=[]
Liste.append(automate.getListInitialStates())
L=automate.listStates
#print(L[0])
#s=automate.getListFinalStates()
#d=automate.getListInitialStates()
t=[automate.getListInitialStates(),automate.getListFinalStates(),automate.listStates]
#print (Liste[0])
#L1=[1]
#L1.append([2])
#print(L1)
#print(t)
#n=automate.listedanslistedeliste(Liste[0],t)
#print(n)
#a=automate.succ(L,"a")
print(a)
