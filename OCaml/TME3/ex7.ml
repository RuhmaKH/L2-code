let rec merge (f: 'a-> 'a ->bool)(l1: 'a list )(l2: 'a list): 'a list=
  match l1,l2 with
    [],[]->[]
  |[],x::xs->l2
  |x::xs,[]->l1
  |x::xs, y::ys->
    if f x y then x::(merge f xs l2) else y::(merge f l1 ys)

let merge_f (f: 'a-> 'a ->bool)(l1: 'a list )(l2: 'a list): 'a list=
  let rec loop (l1: 'a list)(l2: 'a list)(lf : 'a list)=
    match l1,l2 with
      [],[]->lf
    |[],x::xs->lf@l2
    |x::xs,[]->lf@l1
    |x::xs,y::ys->
      if f x y then loop xs l2 (lf@(x::[])) else loop l1 ys (lf@(y::[]))
  in
    loop l1 l2 []

let rec split (l : 'a list): 'a list*'a list =
  match l with
    []->([],[])
  |x::[]->(x::[],[])
  |x::y::xs->
    let (xf,yf)=split(xs) in (x::xf,y::yf)

let split_f (l : 'a list): 'a list*'a list =
  let rec loop (l: 'a list)(xf : 'a list) (yf:'a list)=
    match l with
      []->(xf,yf)
    |x::[]-> (x::xf,yf)
    |x::y::xs-> loop xs (x::xf) (y::yf)
  in
  loop l [] []

let rec merge_sort (f: 'a-> 'a ->bool)(l: 'a list ): 'a list=
  let (l1,l2)=split_f(l) in
  match l1,l2 with
    [],[]->[]
  |[],x::xs->l2
  |x::xs,[]->l1
  |x::xs,y::ys->

let rec padding (l1: 'a list)(l2: 'a list)(x: 'a):'a list * 'a list=
    match l1,l2 with
    [],[]->[],[]
      |a::xs, []-> let (xf,yf)=padding xs [] x  in (a::xf,x::yf)
      |[],a::xs-> let (xf,yf)=padding [] xs x  in (x::xf,a::yf)
      |a::xs,b::ys-> let (xf,yf)=padding xs ys x in (a::xf,b::yf)

let lex (f: 'a-> 'a ->bool)(x: 'a):('a list->'a list->bool)=
  fun l1 l2->
    let (xf,yf)=padding l1 l2 x in
let rec loop (l1:'a list)(l2:'a list)=
match l1,l2 with
    [],[]->false
  |x::xs,y::ys->
    if(f x y) then true else if(x=y) then loop xs ys else false
  |_->false
    in
    loop xf yf

let cmp_bool (b1:bool) (b2:bool) : bool =
    match b1, b2 with
    false, true -> true
      | _ -> false

let sort_bool_list (l: (bool list)list):((bool list)list)=
  merge_sort (lex cmp_bool false) l
