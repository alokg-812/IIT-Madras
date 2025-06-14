var x1 = 10;
{
    console.log(x1);
    var x2 = 20;    //suppose to accessible only in the block
}
console.log(x2);
// x2 is declared in a block but its accessible outside the block also.

let x3 = 30;
{
    console.log(x3);
    let x4 = 40;
}
// console.log(x4); // will generate error

const x5 = 50;
console.log(x5);
// x5 = 55;
// console.log(x5); // will generate error
