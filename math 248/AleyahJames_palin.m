function [count] = JamesAleyah_palin()

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%NAMES: James Barney, Aleyah Dawkins
%JMU-EID: barneyjm, dawkinan
%DATE: 9/16/13
%
%PROGRAM: JamesAleyah_palin.m
%
%PURPOSE: Finds the number of products of three digit positive integers
%that are Palindromic. 
%
%VARIABLES: n: positive integer
%
%JMUPLEDGE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% gives a starting and ending point for the the three digit numbers
start = 100;
theEnd = 1000;
count = zeros(1,1);
% stores the values from start to theEnd
diff = theEnd-start;

for i=start:theEnd-1
    for j=start:theEnd-1
        product = i * j;
        if palin(product) == 1
            count = count + 1;
        end
    end
end

%divides the count by two to eliminate the recount of Palindromic numbers
count = count/2;

end



function [isPalin] = palin(n)

% creates 
isPalin = true;
nAsString = num2str(n);

len = length(nAsString);

for i = 1:length(nAsString)
    
    if nAsString(i) ~= nAsString(len+1-i)
        isPalin = false;
        
    else
        continue
    end
end

%isPalin
end
