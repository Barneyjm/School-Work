function [roots_f_bisect,roots_f_NM,roots_g_bisect,roots_g_NM] = barney_hw7(delta,max_iterations)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% name: James Barney
% jmu-eid: barneyjm
% date: 10/14/2013
% 
% program: barney_hw7.m
% 
% inputs: delta (specified tolerance for converging to the roots
% max_iterations (max iterations to stop while)
% outputs: roots_f_bisect,roots_g_bisect are 1D arrays containing all
% the roots of f(x),g(x), respectively, computed by the
% bisection method
% roots_f_NM,roots_g_NM are 1D arrays containing all the roots
% of f(x),g(x), respectively, computed using Newton's method
%
% JMUPLEDGE
%
% To find intervals and initial guesses, I initially used theorems from FPI
% to get in the ballpark range of the roots (and then later graphed the
% functions and picked values near what I could see to be the roots to
% satisfy the various methods). 
%
% Newton's Method is generally faster. I used the tic/toc method of timing
% things, since writing my own timer is a waste of time. I placed 'tic' at
% the beginning of each loop and 'toc' after the loops' 'end'. This prints
% out the elapsed time of computation. 
%
% I did NOT get the same value for each calculation/method because Newton's
% method can produce more accurate results by doubling the number of
% significant figures for each subsequent iteration. 
% The bisection method is limited in the amount of precision available to
% it. Only one bit of extra precision is added for each iteration,
% ultimately limited by the number of bits available in the computer. 
%
% I looked and looked in the book and online for how to find the imaginary
% roots for function f but I didn't discover anything. I'm assuming that
% the answer lies with handling the division by zero part, but I can't get
% anything to work. :(
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 roots_f_bisect(1) = bisect(1/2, 1.5, @f, delta, max_iterations);
 roots_f_bisect(2) = bisect(2.05, 2.2, @f, delta, max_iterations);

 
 roots_f_NM(1) = NM(-1/2,@f,@fprime,delta,max_iterations);
 roots_f_NM(2) = NM(10,@f,@fprime,delta,max_iterations);

 
 roots_g_bisect(1) = bisect(1/2, 2, @g, delta, max_iterations);
 roots_g_bisect(2) = bisect(2, 5, @g, delta, max_iterations);
 
 roots_g_NM(1) = NM(-5,@g,@gprime,delta,max_iterations);
 roots_g_NM(2) = NM(10,@g,@gprime,delta,max_iterations);
 
end

function root = bisect(a,b,fname,delta,max_iterations)
%--------------------------------
% SUBFUNCTION: bisect
% Purpose: finds a root using the bisection method
%
% Inputs: a,b gives bracketing interval [a,b]
% fname is name of function (f or g)
% delta is tolerance
% max_iterations is maximum iterations until stop
%--------------------------------

N = 0;
foundSolution = 0;
% root starts at MAX INT for error catching
root = intmax;


%  prevents infinite loop
tic
while N < max_iterations,
    
%  computes new midpoint
    c = (a + b)/2;
    eval = fname(c);
%  If f(c) = 0 or (b – a)/2 < delta then we found the solution!
    if eval == 0 || (b - a)/2 < delta
        root = c;
        foundSolution = 1;
        break;
    end
    
    N = N + 1;
%  checks if sign of evaluated value at c == sign of a. Swaps out the
%  appropriate value.
    if (eval < 0 && a < 0) || (eval > 0 && a > 0)
        a = c;
    else
        b = c;
    end
end
fprintf('Time for Bisection Method:\n');
toc

if(foundSolution == 0) 
    fprintf('CAUTION! Maximum iterations reached; Bisection Method Failed\n');
    fprintf('Last approximate root: %f\n', c); 
    root = c;
end
end

function root = NM(x0,fname,fprimename,delta,max_iterations)
%--------------------------------
% SUBFUNCTION: NM
% Purpose: finds a root using Newton's method
%
% Inputs: x0 is initial value
% fname is the name of the function to use (f or g)
% fprimename is the name of the derivative function to use
% (fprime or gprime)
% delta is the tolerance
% max_iterations is maximum iterations until stop
%--------------------------------

 foundSolution = 0; 

 tic
for i = 1:max_iterations
    deriv = fprimename(x0);
     
%  be aware of div by 0
    if abs(deriv) < eps
        fprintf('Dividing by what the computer thinks is zero! ABORT!\n');
        break;
    end
 
%  Newton's calculation
    eval = x0 - fname(x0)/deriv;

%  If the result is within the desired tolerance
    if abs(eval - x0) < delta
%  Store the value of the computed root
        root = eval;
        foundSolution = 1;
        break;
    end
%  Update x0 to start the process again 
    x0 = eval;                                                        

end
fprintf('Time for Newton''s Method:\n');
toc
 
%  No solution found within the desired tolerance
if(foundSolution == 0)   
    fprintf('CAUTION! No solution within desired tolerance; Newtons''s Method Failed\n');
    fprintf('Last approximate root: %f\n', eval); 
    root = eval;
end
end