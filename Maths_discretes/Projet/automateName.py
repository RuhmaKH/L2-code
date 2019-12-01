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
        # transi: Transitions
        for transi in self.getListTransitionsFrom(state):
            if transi.etiquette == lettre and transi.stateDest not in successeurs:
                successeurs.append(transi.stateDest)

        return successeurs


    def succ (self, listStates, lettre):
        """list[State] x str -> list[State]
        rend la liste des états accessibles à partir de la liste d'états
        listStates par l'étiquette lettre
        """
        successeurs = []
        #state: State
        for state in listStates:
            #transi: Transition
            for transi in self.getListTransitionsFrom(state):
                if transi.etiquette == lettre and transi.stateDest not in successeurs:
                    successeurs.append(transi.stateDest)

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
        #listState: list[States]
        listeState = auto.getListInitialStates()
        #lettre : str
        for lettre in mot:
            listeState = auto.succ(listeState, lettre)

        return State.isFinalIn(listeState)


    @staticmethod
    def estComplet(auto,alphabet) :
        """ Automate x str -> bool
         rend True si auto est complet pour alphabet, False sinon
        """
        #state : State
        for state in auto.listStates:
            #lettre : str
            for lettre in alphabet:
                if auto.succElem(state, lettre) == []:
                    return False

        return True


    @staticmethod
    def estDeterministe(auto) :
        """ Automate  -> bool
        rend True si auto est déterministe, False sinon
        """ 
        #state : State
        for state in auto.listStates:
            #lettre : str
            for lettre in auto.getAlphabetFromTransitions():
                if len(auto.succElem(state,lettre)) > 1:
                    return False

        return True


    @staticmethod
    def completeAutomate(auto,alphabet) :
        """ Automate x str -> Automate
        rend l'automate complété d'auto, par rapport à alphabet
        """
        #newAuto : Automate
        newAuto = Automate(auto.listTransitions)

        if(Automate.estComplet(newAuto,alphabet)):
            return newAuto

        #listeState : list[State]
        listeState = newAuto.listStates
        #trashState : State
        trashState = State(len(listeState)+1, False, False, "Trash")
        newAuto.addState(trashState)
        #state : State
        for state in listeState:
            #lettre : str
            for lettre in alphabet:
                if newAuto.succElem(state, lettre) == []:
                    newAuto.addTransition(Transition(state, lettre, trashState))

        return newAuto


    @staticmethod
    def determinisation(auto) :
        """ Automate  -> Automate
        rend l'automate déterminisé d'auto
        """
        #newAuto : Automate
        newAuto = Automate(auto.listTransitions)

        if(Automate.estDeterministe(newAuto)):
            return newAuto

        #listeSet : list(list[States])
        listeSet = [newAuto.getListInitialStates()]    #Notre liste d'attente
        #listeFinal : list[States]
        listeFinal = newAuto.getListFinalStates()
        #tempListe : list[list[States]]
        tempListe = []
        #newTransition: list[Transition]
        newTransition = []
        #final : bool
        final = False

        for state in listeSet[0]:
            if state in listeFinal:
                final = True
                break
        #newInit : States
        newInit = State(0, True, final, str(listeSet[0]))
        #newStates : set(States)
        newStates = set()
        newStates.add(newInit)
        final = False
        #listeOccured: list[list[States]]
        listeOccured = [listeSet[0]]
        #id : int
        id = 1
        #tag : str
        tag = ""

        while(listeSet != []):
            tag = str(listeSet[0])
            for state in newStates:
                if tag == state.label:
                    newInit = state
                    break
            for lettre in newAuto.getAlphabetFromTransitions():
                tempListe = newAuto.succ(listeSet[0], lettre)
                if tempListe in listeOccured:
                    tag = str(tempListe)
                    for state in newStates:
                        if tag == state.label:
                            newTransition.append(Transition(newInit, lettre, state))
                            break
                else:
                    if tempListe != []:
                        listeOccured.append(tempListe)
                        #Test pour voir si l'état sera final
                        for state in tempListe:
                            if state in listeFinal:
                                final = True
                        tag = str(tempListe)
                        tempState = State(id, False, final, tag)
                        id += 1
                        final = False
                        newTransition.append(Transition(newInit, lettre, tempState))
                        newStates.add(tempState)
                        listeSet.append(tempListe)

            del listeSet[0]

        return Automate(newTransition)



    @staticmethod
    def complementaire(auto,alphabet):
        """ Automate -> Automate
        rend  l'automate acceptant pour langage le complémentaire du langage de auto
        """
        #newAuto : Automate
        newAuto = Automate(auto.listTransitions)
        newAuto = Automate.completeAutomate(newAuto,alphabet)
        newAuto = Automate.determinisation(newAuto)

        for state in newAuto.listStates:
             state.fin= not (state.fin)

        return newAuto


    @staticmethod
    def intersection (auto0, auto1):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage l'intersection des langages des deux automates
        """
        if(auto0.listTransitions==auto1.listTransitions):
            return Automate(auto0.listTransitions)
        ################### Creation des Variables utiles au programme ########
        #id : int
        id = 0
        #final : boolean
        final = False
        #listeTransi: list[Transition]
        listeTransi = []
        #listeOccured ; list[tuple(States)]
        listeOccured = []
        #tempState_1 : States
        tempState_1 = State(0, True, True, "trash")
        #tempState_2 : States
        tempState_2 = State(0, True, True, "trash1")
        ################# Création de l'alphabet #############################
        #alphabet : str
        alphabet = auto0.getAlphabetFromTransitions()
        for lettre in auto1.getAlphabetFromTransitions():
            if lettre not in alphabet:
                alphabet += lettre
        ################# Création de la liste des States initiaux ###############
        #listeTuple : list[tuple(States)]
        listeTuple = []    #Notre liste d'attente
        for init_1 in auto0.getListInitialStates():
            for init_2 in auto1.getListInitialStates():
                print("      \n"+ str(init_1)+str(init_2))
                listeTuple.append((init_1, init_2))
                listeOccured.append((init_1, init_2))
        ################ Création de la liste des States des états finaux #########
        #listeFin : list[States]
        listeFin = auto0.getListFinalStates()
        for fin in auto1.getListFinalStates():
            if fin not in listeFin:
                listeFin.append(fin)
        ################ Création de l'ensemble des States du nouvel automate #########
        #newStates ; set(State)
        newStates = set()
        for tup in listeTuple:
            final = (tup[0] in listeFin) and (tup[1] in listeFin)
            newStates.add(State(id, True, final, "("+ str (tup[0])+ " ; "+ str (tup[1])+ ")") )
            final = False
            id += 1
        ################ Parcours de la Liste d'attente #############################
        while(listeTuple != []):
            tag = "("+ str (listeTuple[0][0]) + " ; "+str (listeTuple[0][1])+")"
            for state in newStates:
                if tag == state.label:
                    tempState_1 = state
                    break

            for lettre in alphabet:
                for succ_1 in auto0.succElem(listeTuple[0][0], lettre):
                    for succ_2 in auto1.succElem(listeTuple[0][1], lettre):
                        if (succ_1,succ_2) in listeOccured:
                            tag = "("+str (succ_1) +" ; "+ str (succ_2)+")"
                            for state in newStates:
                                if tag == state.label:
                                    tempState_2 = state
                                    listeTransi.append(Transition(tempState_1, lettre, tempState_2))
                                    break

                        else:
                            final = (succ_1 in listeFin) and (succ_2 in listeFin)
                            listeOccured.append((succ_1, succ_2))
                            tag = "("+str (succ_1) + " ; "+str (succ_2)+")"
                            tempState_2 = State(id, False, final, tag)
                            id += 1
                            final = False
                            listeTransi.append(Transition(tempState_1, lettre, tempState_2))
                            newStates.add(tempState_2)
                            listeTuple.append((succ_1, succ_2))
            del listeTuple[0]

        return Automate(listeTransi)


    @staticmethod
    def union (auto1, auto2):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage l'union des langages des deux automates
        """
        #listInit_1 : list[States]
        listInit_1 = auto1.getListInitialStates()
        #listInit_2 : list[States]
        listInit_2 = auto2.getListInitialStates()
        #oldTransition_1: list[Transition]
        oldTransition_1 = auto1.listTransitions
        #oldTransition_2: list[Transition]
        oldTransition_2 = auto2.listTransitions
        #newTransition : List[Transition]
        newTransition = oldTransition_1 + oldTransition_2
        #newInit : State
        newInit = State(0, True, False, "0")
        
        for state in auto1.listStates:
            state.insertPrefix(1)

        for state in auto2.listStates:
            state.insertPrefix(2)

        for state in (listInit_1 + listInit_2):
            state.init = False
            if state in (auto1.getListFinalStates() + auto2.getListFinalStates()):
                newInit.init = True

        for transi in (oldTransition_1 + oldTransition_2):
            if transi.stateSrc in (listInit_1 + listInit_2):
                newTransition.append(Transition(newInit, transi.etiquette, transi.stateDest))

        return Automate(newTransition)


    @staticmethod
    def concatenation (auto1, auto2):
        """ Automate x Automate -> Automate
        rend l'automate acceptant pour langage la concaténation des langages des deux automates
        """
        #newAuto1 : Automate
        newAuto_1 = Automate(auto1.listTransitions)
        #newAuto2 : Automate
        newAuto_2 = Automate(auto2.listTransitions)
        #listeFin : list[States]
        listeFin = newAuto_1.getListFinalStates()
        #listeInit : list[States]
        listeInit = newAuto_2.getListInitialStates()
        #newTransition : List[Transition]
        newTransition = newAuto_1.listTransitions

        for state in newAuto_1.listStates:
            state.insertPrefix(1)
            if state in listeFin:
                state.fin = False

        for state in newAuto_2.listStates:
            state.insertPrefix(2)

        for final in listeFin:
            for transi in newAuto_2.listTransitions:
                if transi.stateSrc in listeInit:
                    transi.stateSrc = final
                if transi.stateDest in listeInit:
                    transi.stateDest = final
                if transi in newTransition:
                    continue
                newTransition.append(transi)
                
        return Automate(newTransition)


    @staticmethod
    def etoile (auto):
        """ Automate  -> Automate
        rend l'automate acceptant pour langage l'étoile du langage de a
        """
        #newAuto : Automate
        newAuto = Automate(auto.listTransitions)
        #listeInit : list[States]
        listeInit = newAuto.getListInitialStates()
        #listeFin : list[States]
        listeFin = newAuto.getListFinalStates() 
        #newTransi : List[Transition]
        newTransi=[]
        #falseInit : State
        falseInit = State(len(auto.listStates)+1,False,False,"X")
        
        for state in listeInit:
            state.fin = True

        for final in listeFin:
            for transi in newAuto.listTransitions:
                if transi.stateSrc in listeInit:
                    if transi.stateDest in listeInit:
                        newTransi.append(Transition(falseInit, transi.etiquette, falseInit))
                    else:
                        newTransi.append(Transition(final, transi.etiquette, transi.stateDest))
                        newTransi.append(Transition(falseInit, transi.etiquette, transi.stateDest))
                if (transi.stateSrc in listeFin) and (transi.stateDest in listeInit):
                    newTransi.append(Transition(final, transi.etiquette, falseInit))
                else:
                    newTransi.append(transi)

        return Automate(newTransi)


automate=Automate.creationAutomate("exempleAutomate.txt")
automate1=Automate.creationAutomate("auto.txt")
a=Automate.etoile(automate)
print(a)
a.show("étoile")
'''
a=Automate.intersection(automate,automate1)
a=Automate.determinisation(automate)
print (automate)
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

b=Automate.determinisation(automate1)
print (b)
'''
