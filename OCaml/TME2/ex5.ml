let float_of_3int (a:int*int*int):(float*float*float)=
  let (a1,a2,a3)= a in (float_of_int(a1),float_of_int(a2),float_of_int(a3))

let valeur_poly (d:int*int*int)(x:float):(float)=
  let (a,b,c)=float_of_3int(d) in (a *. x *. x +. b *. x +. c )

let discriminant (d:int*int*int):int=
  let (a,b,c)=d in b*b-4*a*c

let nb_solution (d:int):int=
  if(d=0)then  1 else
  if(d>0)then(2)else(0)

let solution()
