let f (a:float)(x:float):float=
  1./.2.*.(x+.a/.x)

let rec sqrt_n (n:int)(a:float)(x0:float):float=
  let rec loop (n:int)(x:float):float =
    if(n=0)then x
    else loop(n-1)(f a x)
  in
  loop n x0

let eq_eps(e:float)(x:float)(y:float):bool=
  y-.x<e

let rec sqrt_x(e:float)(a:float)(x0:float):float=
  let rec loop (x:float):float =
    if eq_eps e x (f a x) then x
    else loop (f a x)
  in
  loop x0
