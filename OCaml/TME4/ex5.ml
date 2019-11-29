type task = (int*string)
type 'a btree =
    Empty
  | Node of 'a * 'a btree * 'a btree

let rec insert_task (t:task)(b:task btree): task btree=
  let (p1,t1)=t in
  match b with
    Empty->Node(t,Empty,Empty)
  |Node((p2,t2),g,d)->
    if(p1<p2) then Node((p2,t2),insert_task t g,d)
    else Node((p2,t2),g,insert_task t d)

let rec take_max (t:task btree):string=
  match t with
    Empty -> raise(Invalid_argument "t")
  |Node((t1,p1),_,Empty)->p1
  |Node(_,_,d)->take_max d

let rec remove_max (t:task btree): task btree=
match t with
  Empty -> raise(Invalid_argument "t")
|Node((t1,p1),g,Empty)->g
|Node(x,g,d)->Node(x,g,remove_max d)
