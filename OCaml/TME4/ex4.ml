type 'a btree =
    Empty
  | Node of 'a * 'a btree * 'a btree

let x = Node(2,
             Node(1,Empty,Empty),
             Node(3,
                  Node(2,Empty,Empty),
                  Node(4,Empty,Empty)))

let rec insert (a: 'a)(t : 'a btree): 'a btree=
  match t with
    Empty-> Node(a, Empty, Empty)
  |Node (x,g,d)-> if (a<x) then Node(x,insert a g,d) else Node(x,g,insert a d)

let rec from_list (l:'a list):'a btree=
  match l with
    []->Empty
  |x::xs-> insert x (from_list xs)

let from_list_t (l:'a list):'a btree=
  let rec loop (l:'a list)(a:'a btree):'a btree=
    match l with
      []->a
    |x::xs-> loop xs (insert x a)
  in
    loop l Empty

let from_list_fold (l:'a list): 'a btree=
  List.fold_left (fun t x -> insert x t) Empty l

let rec to_list(a:'a btree):('a list)=
  match a with
    Empty->[]
  |Node(x,g,d)-> (to_list g) @ [x] @ (to_list d)

let tri (l:'a list):'a list=
  to_list (from_list_t l)
