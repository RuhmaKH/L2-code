let  rec bale( n:int):float=
  if (n=1) then 1. else 1. /.(float_of_int(n*n)) +. bale(n-1)

let bale_t(n:int):float=
  let rec loop (n:int)(x:float):float=
    if (n=1) then x
    else loop(n-1)(1./.(float_of_int(n*n))+.x)
in
  loop n 1.
