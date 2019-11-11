#define TT 128

int copie(char* f[]){
    int fd, fdDest, somme, n, i, size;
    char buf[TT];
    size = sizeof(f);
    fdDest = open(f[size-1],O_WRONLY);
    if(fdDest<0){
        perror("open");
    }
    for(i=0;i<size-1;i++){
        fd = open(f[i],O_RDONLY);
        if(fd<0 ){
            perror("open");
            return -1;
        }
        while((n=read(fd,buf,TT))>0){
            if(n< 0){
                perror("read");
                return -1;
            }
            if(write(fdDest,buf[n],n)<0){
                perror("write");
                return -1;
            }
            somme += n;
            if(n==0){
                break;
            }
        }
        close(fd);
    }
    close(fdDest);
    return somme;
}