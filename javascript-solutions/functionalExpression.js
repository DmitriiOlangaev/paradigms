"use strict"

const createEvaluableExpression = (f) => {
    const fnc = (...args) => (...values) => f(...args.map(arg => arg(...values)));
    fnc.argc = f.length;
    return fnc;
}

const cnst = (value) => () => value;

const pi = cnst(Math.PI);
const e = cnst(Math.E);

const constants = {
    "pi": pi,
    "e": e
}

const varNames = {
    "x": 0,
    "y": 1,
    "z": 2
}

const variable = (name) => (...args) => args[varNames[name]];

const abs = createEvaluableExpression((a) => Math.abs(a));

const iff = createEvaluableExpression((a, b, c) => a >= 0 ? b : c);

const add = createEvaluableExpression((a, b) => a + b);

const subtract = createEvaluableExpression((a, b) => a - b);

const multiply = createEvaluableExpression((a, b) => a * b);

const divide = createEvaluableExpression((a, b) => a / b);

const negate = createEvaluableExpression((x) => -x);

const operations = {
    "+": add,
    "-": subtract,
    "*": multiply,
    "/": divide,
    "negate": negate,
    "abs": abs,
    "iff": iff,
}

const apply = (stack, f) => {
    // let args = [];
    // for (let i = 0; i < f.argc; ++i) {
    //     args.unshift(stack.pop());
    // }
    stack.push(f(...stack.splice(stack.length - f.argc, f.argc)));
}

const parse = (expression) => {
    let tokens = expression.split(" ").filter(s => s.length !== 0);
    let res = [];
    for (const token of tokens) {
        if (constants[token] !== undefined) {
            res.push(constants[token]);
        } else if (varNames[token] !== undefined) {
            res.push(variable(token));
        } else if (operations[token] !== undefined) {
            apply(res, operations[token]);
        } else {
            res.push(cnst(+token));
        }
    }
    return res[0];
}
