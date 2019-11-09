#define TT 128

int copie(char* f1,char* f2,char* f3){
    int fd1,fd2,fd3, somme, n;
    char buf[TT];
    fd1 = open(f1,O_RDONLY);
    fd2 = open(f2,O_RDONLY);
    fd3 = open(f3,O_RDONLY);
    if((fd1 || fd2 || fd3)<0 ){
        perror("open");
        return -1;
    }
    while((n=read(fd1,buf,TT))>0){
        if(n< 0){
            perror("read");
            return -1;
        }
        if(write(fd3,buf[n],n)<0){
            perror("write");
            return -1;
        }
        somme += n;
        if(n==0){
            break;
        }
    }
    while((n=read(fd2,buf,TT))>0){
        if(n< 0){
            perror("read");
            return -1;
        }
        if(write(fd3,buf[n],n)<0){
            perror("write");
            return -1;
        }
        somme += n;
        if(n==0){
            break;
        }
    }
    close(fd1,fd2,fd3);
    return somme;
}