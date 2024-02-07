#include<stdio.h>
void print(int x) {
    if(x == 0) return;
    print(x-1);
    printf("%d\n",x);
}
int main() {
    int n;
    printf("Enter the n: ");
    scanf("%d",&n);
    print(n);
}