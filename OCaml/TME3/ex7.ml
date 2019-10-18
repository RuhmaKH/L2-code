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
  let (l1,l2)=split(l) in
  match l1,l2 with
    [],[]->merge_f f l1 l2
  |[],x::xs->merge_f f l1 (x::l2)
  |x::xs,[]->merge_f f (x::l1) l2
  |x::xs,y::ys->merge_sort f (merge_f f (x::l1) (y::l2))
