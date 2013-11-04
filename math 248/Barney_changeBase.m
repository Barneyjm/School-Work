function [num] = Barney_changeBase(in, n, out)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%NAMES: James Barney
%JMU-EID: barneyjm
%DATE: 9/23/13
%
%PROGRAM: Barney_changeBase.m
%
%PURPOSE: changes the base of the number given to the base desired. 
%
%VARIABLES: n: positive integer
%           in, out: the input base and output base, respectively
%           num: the placeholder for return the value
%           newNum: the calculated new-based number
%           i: an iterator
%           rem: the remainder of division useful for next calculations
%           decimalPart: the decimal part of the conversion (not
%           completed--see below)
%           t: place holder for looped calculations
%           strN: string representation of numbers
%           order: 'length' of the number
%           fract: the fractional part of a number (not implemented)
%           intRep: the integer representation of a fraction (not
%           implemented)
%
%COMMENT:
%   Dr. Tongen, I'm sorry that this does not handle decimal values at all.
%   I know how to do it but I'm out of time and would rather submit
%   something that works for integers well than something that might break.
%   I was slammed by my job and didn't expect to have to work as much as I
%   did this past weekend and wasn't able to complete this assignment. I'm
%   quite ashamed that I didn't complete this to the best of my ability,
%   since I'm completely capable of doing so. Just wanted to let you know.
%   --James Barney
%
%JMUPLEDGE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% in = zeros(1,1);
% n = zeros(1,1);
% out = zeros(1,1);
num = zeros(1,1);

% determines which operation to do
if in == 10
    num = base10toB(in, n, out);
else
    num = baseBto10(in, n, out);
end

end

function [newNum] = base10toB(in, n, out)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% 
% just works for integers!
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
newNum = zeros(1,10);
i = 1;


while n ~= 0,
    rem = mod(n,out); %the remainder is used in the next iteration
    n = floor(n/out); %grabs the integer part of the number
    newNum(i) = rem; %stores the value for later use
    i = i + 1; %increments the loop
    
end

%this code handles the array that stored the computed values
newNum = fliplr(newNum); %flips the array around (since it calculated it in reverse)
newNum = newNum(find(newNum,1,'first'):length(newNum)); %trims off the preceeding zeros from instantiation
%newNum = str2double(strcat(mat2str(newNum), dealWithDecimals(in, n, out)));
 

end

function [newNum] = baseBto10(in, n, out)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% just works for integers!
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

decimalPart = zeros(1,1);

strN = fliplr(num2str(n)); %flips the number around for Horner's Algorithm
order = length(strN); %finds the "length" of the number
t = str2double(strN(order)); %instantiates the temp variable, t.

for i = order-1:-1:1
    %Horner's Algorithm.
    t = t*in + str2double(strN(i));
    
end

%stores the number as a string and prepares it for concatenation with the
%decimal part then converts it back to a number for returning.
newNum = str2double(strcat(num2str(t), '.', num2str(decimalPart)));
end

function [intRep] = dealWithFractions(in, n, out)
%%%%%%%%%%%%%%%%%
%
% Not implemented -- out of time.
%
%%%%%%%%%%%%%%%%%
fract = mod(n,1);
strRep = num2str(fract);

intRep = fract*10^(length(strRep)-2);

%intRep = (baseBto10(in, intRep, out))/in^(length(strRep)-2);

end
