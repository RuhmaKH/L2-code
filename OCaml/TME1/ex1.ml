let f (x:int)(y:int) :int =x+y+2
let()=assert((f 2 3)=7)
let () =assert((f(-1)(-1))=0)
