"use strict"

function ConstVar(value) {
    this.value = value;
}

function createCV(df, evaluate) {
    function CV(arg) {
        ConstVar.call(this, arg);
    }

    CV.prototype = Object.create(ConstVar.prototype);
    CV.prototype.constructor = CV;
    CV.prototype.evaluate = evaluate;
    CV.prototype.diff = df;
    return CV;
}

ConstVar.prototype.prefix = function () {
    return this.value.toString();
}
ConstVar.prototype.postfix = function () {
    return this.value.toString();
}

ConstVar.prototype.toString = function () {
    return this.value.toString();
}

const Const = createCV(function (diffVar) {
    return ZERO;
}, function () {
    return this.value;
})

const varNames = {
    "x": 0,
    "y": 1,
    "z": 2
}

const Variable = createCV(function (diffVar) {
    return this.value === diffVar ? ONE : ZERO;
}, function (...args) {
    return args[varNames[this.value]];
})

const ZERO = new Const(0);
const ONE = new Const(1);
const TWO = new Const(2);
const E = new Const(Math.E);

function Operation(...args) {
    this.args = args;
}

function createOperation(f, df, sign) {
    function Op(...args) {
        Operation.call(this, ...args);
    }

    Op.prototype = Object.create(Operation.prototype);
    Op.prototype.constructor = Op;
    Op.prototype.apply = f;
    Op.prototype.df = df;
    Op.prototype.sign = sign;
    return Op;
}

Operation.prototype.prefix = function () {
    return '(' + this.sign + ' ' + this.args.map(arg => arg.prefix()).join(' ') + ')';
}

Operation.prototype.postfix = function () {
    return '(' + this.args.map(arg => arg.postfix()).join(' ') + ' ' + this.sign + ')';
}

Operation.prototype.evaluate = function (...args) {
    return this.apply(...this.args.map(arg => arg.evaluate(...args)));
}

Operation.prototype.diff = function (diffVar) {
    return this.df(...this.args, ...this.args.map(arg => arg.diff(diffVar)));
}

Operation.prototype.toString = function () {
    return [...this.args, this.sign].join(' ');
}

const POW2 = x => new Multiply(x, x);
const LN = x => new Log(E, x);
const Add = createOperation((x, y) => x + y, (x, y, dx, dy) => new Add(dx, dy), '+');
const Subtract = createOperation((x, y) => x - y, (x, y, dx, dy) => new Subtract(dx, dy), '-');
const Multiply = createOperation((x, y) => x * y, (x, y, dx, dy) => new Add(new Multiply(dx, y), new Multiply(x, dy)), '*');
const Divide = createOperation((x, y) => x / y, (x, y, dx, dy) => new Divide(new Subtract(new Multiply(dx, y), new Multiply(x, dy)), POW2(y)), '/');
const Negate = createOperation(x => -x, (x, dx) => new Negate(dx), "negate");
const Pow = createOperation(Math.pow, (x, y, dx, dy) => new Multiply(new Pow(x, y), new Add(new Multiply(dy, LN(x)), new Divide(new Multiply(dx, y), x))), "pow");
const Log = createOperation((x, y) => Math.log(Math.abs(y)) / Math.log(Math.abs(x)),
    (x, y, dx, dy) => new Divide(new Subtract(new Multiply(new Divide(dy, y), LN(x)), new Divide(new Multiply(LN(y), dx), x)), POW2(LN(x))),
    "log");

const add = (x, y) => new Add(x, y);

const Mean = createOperation((...args) => args.reduce((prev, cur) => prev + cur) / args.length,
    (...args) => new Divide((args.slice(args.length / 2, args.length)).reduce(add), new Const(args.length / 2)),
    "mean");
//сумма квадратов / количество - квадрат суммы / квадрат количества
const Var = createOperation((...args) => {
        let left = 0;
        let right = 0;
        for (const e of args) {
            left += e * e;
            right += e;
        }
        return (left * args.length - Math.pow(right, 2)) / Math.pow(args.length, 2);
        // return args.map(arg => arg * arg).reduce((prev, cur) => prev + cur) / args.length - Math.pow(args.reduce((prev, cur) => prev + cur), 2) / Math.pow(args.length, 2)
    },
    (...args) => {
        let left = ZERO;
        let right1 = ZERO;
        let right2 = ZERO;
        for (let i = 0; i < args.length / 2; ++i) {
            left = new Add(left, new Multiply(args[i], args[args.length / 2 + i]));
            right1 = new Add(right1, args[i]);
            right2 = new Add(right2, args[i + args.length / 2]);
        }
        return new Subtract(
            new Multiply(TWO, new Divide(left,
                new Const(args.length / 2))),
            new Multiply(TWO, new Divide(new Multiply(right1, right2), new Const(Math.pow(args.length / 2, 2)))))
    },
    "var");

const operations = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide,
    "negate": Negate,
    "pow": Pow,
    "log": Log,
    "mean": Mean,
    "var": Var
}

const parse = (expression) => {
    let tokens = expression.split(" ").filter(s => s.length !== 0);
    let res = [];
    for (const token of tokens) {
        if (token in varNames) {
            res.push(new Variable(token));
        } else if (token in operations) {
            let temp = operations[token].prototype.apply.length;
            res.push(new operations[token](...res.splice(res.length - temp, temp)));
        } else {
            res.push(new Const(+token));
        }
    }
    return res[0];
}

function ParseError(message) {
    Error.call(this, message);
    this.message = message;
}

ParseError.prototype = Object.create(Error.prototype);
ParseError.constructor = ParseError;
ParseError.prototype.name = "ParseError";

function Parser(expression) {
    this.expression = expression;
    this.it = 0;
}


Parser.prototype.constructor = Parser;

Parser.prototype.skipSpaces = function () {
    while (this.it !== this.expression.length && this.take(' ')) {
        //nothing
    }
}

Parser.prototype.take = function (expected) {
    if (expected === undefined) {
        if (this.it === this.expression.length) {
            throw new ParseError("End of expression");
        }
        return this.expression[this.it++];
    }
    if (this.test(expected)) {
        this.it += expected.length;
        return true;
    }
    return false;
}

Parser.prototype.test = function (expected) {
    return this.expression.length - this.it >= expected.length && this.expression.substring(this.it, this.it + expected.length) === expected;
}

Parser.prototype.expect = function (expected) {
    if (!this.take(expected)) {
        throw new ParseError("Expect " + expected);
    }
}

Parser.prototype.parse = function (flag) {
    let result = this.parseArguments(flag);
    if (this.it !== this.expression.length) {
        throw new ParseError("excessive tokens ");
    }
    if (result.length !== 1) {
        throw new ParseError("excessive operations, I got TL why????????");
    }
    return result[0];
}

Parser.prototype.parseExpression = function (flag) {
    this.skipSpaces();
    this.expect('(');
    let op;
    let args;
    if (flag) {
        op = this.parseOperation();
        args = this.parseArguments(flag);

    } else {
        args = this.parseArguments(flag);
        op = this.parseOperation();
    }
    if (op.prototype.apply.length !== args.length && op.prototype.apply.length !== 0) {
        throw new ParseError(op.prototype.sign + " discrepancy between the number of arguments and the operation");
    }
    this.skipSpaces();
    this.expect(')');
    return new op(...args);
}

Parser.prototype.parseOperation = function () {
    this.skipSpaces();
    for (const property in operations) {
        if (this.take(property)) {
            if (this.it !== this.expression.length && !(this.test(' ') || this.test('(') || this.test(')'))) {
                throw new ParseError("Expect space or brace after sign of operation");
            }
            return operations[property];
        }
    }
    throw new ParseError("Expect some operation");
}

Parser.prototype.parseArguments = function (flag) {
    let res = [];
    this.skipSpaces();
    while (true) {
        if (this.test('(')) {
            res.push(this.parseExpression(flag));
            this.skipSpaces();
        }
        if (this.it === this.expression.length) {
            break
        }
        if (this.test(')')) {
            break;
        }
        let t = -1;
        for (let i = this.it; i < this.expression.length; ++i) {
            if (this.expression[i] === ' ' || this.expression[i] === '(' || this.expression[i] === ')') {
                t = i;
                break;
            }
        }
        if (t === -1) {
            t = this.expression.length;
        }
        let token = this.expression.substring(this.it, t);
        if (token === "") {
            //nothing - case ()()
        } else if (!isNaN(+token)) {
            res.push(new Const(+token));
        } else if (token in varNames) {
            res.push(new Variable(token));
        } else {
            break;
        }
        this.it = t;
        this.skipSpaces();
    }
    return res;
}

const parsePrefix = expression => {
    return new Parser(expression).parse(true);
}

const parsePostfix = expression => {
    return new Parser(expression).parse(false);
}
