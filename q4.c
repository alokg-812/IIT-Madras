#include<stdio.h>
int fibo(int x) {
    if(x==2||x==1)return 1;
    int a = fibo(x-1);
    int b = fibo(x-2);
    int ans = a + b;
    return ans;
}
int main() {
    int n;
    printf("Enter the n: ");
    scanf("%d",&n);
    printf("%d",fibo(n));
    return 0;
}