composite(1).

init(MAX_N) :- exclude(4, 2, MAX_N), sieve(3, MAX_N).

sieve(VALUE, BOUND) :- SQUARE is VALUE * VALUE, SQUARE > BOUND, !.
sieve(VALUE, BOUND) :- mark(VALUE, BOUND), NEW_VALUE is VALUE + 2, sieve(NEW_VALUE, BOUND).

mark(VALUE, BOUND) :- NEW_VALUE is VALUE * VALUE, exclude(NEW_VALUE, VALUE, BOUND).

exclude(VALUE, STEP, BOUND) :- VALUE > BOUND, !.
exclude(VALUE, STEP, BOUND) :- \+ composite(VALUE), assert(composite(VALUE)), assert(min_div(VALUE, STEP)), fail.
exclude(VALUE, STEP, BOUND) :- NEW_VALUE is VALUE + STEP, exclude(NEW_VALUE, STEP, BOUND).

prime(N) :- \+composite(N).

prime_divisors(1, []).
prime_divisors(VALUE, [VALUE]) :- prime(VALUE), !.
prime_divisors(VALUE, [DIVISOR | TAIL]) :- number(VALUE), min_div(VALUE, DIVISOR), NEW_VALUE is div(VALUE, DIVISOR), prime_divisors(NEW_VALUE, TAIL), !.
prime_divisors(VALUE, [DIVISOR1, DIVISOR2 | TAIL]) :- number(DIVISOR1), number(DIVISOR2), DIVISOR1 =< DIVISOR2, prime(DIVISOR1), prime_divisors(NEW_VALUE, [DIVISOR2 | TAIL]), VALUE is NEW_VALUE * DIVISOR1.

prime_palindrome(VALUE, BASE) :- prime(VALUE), check_palindrome(VALUE, BASE, ARRAY), reverse(ARRAY, ARRAY1), ARRAY = ARRAY1.

check_palindrome(0, BASE, []) :- !.
check_palindrome(VALUE, BASE, [FIRST | TAIL]) :- FIRST is VALUE mod BASE, NEW_VALUE is div(VALUE, BASE), check_palindrome(NEW_VALUE, BASE, TAIL).
