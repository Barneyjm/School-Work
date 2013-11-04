function Barney_bitbybit(seed)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%NAME: James Barney
%JMU-EID: barneyjm
%DATE: 9/30/13
%
%PROGRAM: Barney_bitbybit.m
%
%PURPOSE: shows floating point cut off 
%
%VARIABLES: 
%           input, the seed given when starting program
%           storeVal = the storage for the 100 computed values
%
%
% JMU PLEDGE
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

input = seed;
storeVal = zeros(1,101);
storeVal(1) = input;

for i = 2:101
% The following algorithm gives us yucky round-off error after a few
% iterations resulting in catastrophic failure: 
%     if input * 2 < 1
%         input = 2*input;
%     else
%         input = 2*input - 1;
%     end

%the slick and clever way of doing this FPI using mod()
    input = mod(2*input, 1);
    storeVal(i) = input;
   
end
storeVal;
%plots the results to see the error propogate.
plot(storeVal);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Rational conditions, like x = 0.2, results in an oscillating calculation
% of various orbits depending on the rational input. Irrational conditions
% result in a wildly oscillating calculation for each iteration. 
%
% 1. The results with my program start off matching my hand calculations.
% But when the program continues to run for about 30 iterations, the 
% changes begin to mount up and eventually result in a catastrophic
% failure of the simulation.
% 2. Both rational and irrational numbers fail, although some numbers take
% longer to fail than others. 
% 3. Since irrational numbers cannot be stored in finite memory, the
% computer has to decide when to cut off the number and store it
% as the closest representation it can compute with a floating point number.
% This is alright for the first couple of iterations, 
% but eventually the chopping effect of estimating the irrational numbers
% adds up to a significant number for the calculation and drastically 
% alters the input for the next loop, setting the program to inaccurately
% display the actual values. 
% 4. To fix the problem we need to use an alternative method of computing
% fixed-point iteration problems which was given to us in the problem. 


end