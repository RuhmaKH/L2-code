(* Les 3 fonctions ci-dessous sont à utiliser pour la fin du tme. Vous
   compléterez les annotations de types des fonctions suivantes en utilisant les
   types que vous aurez définis pendant le tme *)

let i2b (n: int) =
  n <> 0

let i2q (n: int) =
  let b0 = i2b (n mod 2) in
  let b1 = i2b (n/2 mod 2) in
  let b2 = i2b (n/4 mod 2) in
  let b3 = i2b (n/8 mod 2) in
    (b3,b2,b1,b0)

let i2o (n: int) =
  let b0 = i2b (n mod 2) in
  let b1 = i2b (n/2 mod 2) in
  let b2 = i2b (n/4 mod 2) in
  let b3 = i2b (n/8 mod 2) in
  let b4 = i2b (n/16 mod 2) in
  let b5 = i2b (n/32 mod 2) in
  let b6 = i2b (n/64 mod 2) in
  let b7 = i2b (n/128 mod 2) in
    (b7,b6,b5,b4,b3,b2,b1,b0)

let xor(x:bool)(y:bool):bool=
  (x||y)&&not(x&&y)
let ()=assert ((xor true true)=false)

type cmp = bool*bool*bool

let cmp1(x:bool)(y:bool):cmp=
  let s1= (xor x y)&& (not x) in
  let s2=not (xor x y) in
  let s3=(xor x y)&&not y in
  (s1,s2,s3)

let lex(r1:cmp)(r2:cmp):cmp=
  match r1, r2 with
  (true,false,false),_->(true,false,false)
  |(false,false,true),_->(false,false,true)
  |_,(true,false,false)->(true,false,false)
  |_,(false,false,true)->(false,false,true)
  |_,_->(false,true,false)

let lex_bis(r1:cmp)(r2:cmp):cmp=
  let (s1,s2,s3)=r1 in
  let (s4,s5,s6)=r2 in
  (s1 || (s2 && s4),s2 && s5,s3 || (s2 && s6))

type duet = bool*bool

let cmp2(a:duet)(b:duet):cmp=
  let (a1,a2)=a in
  let (b1,b2)=b in
  lex (cmp1 a1 b1) (cmp1 a2 b2)

type quartet = (duet*duet)

let cmp4(a:quartet)(b:quartet):cmp=
  let (a1,a2)=a in
  let (b1,b2)=b in
  lex (cmp2 a1 b1) (cmp2 a2 b2)

type octet = quartet*quartet

let cmp8(a:octet)(b:octet):cmp=
  let (a1,a2)=a in
  let (b1,b2)=b in
  lex (cmp4 a1 b1) (cmp4 a2 b2)