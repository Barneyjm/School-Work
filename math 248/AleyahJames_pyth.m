function [Ans]=JamesAleyah_pyth(n)


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%NAMES: James Barney, Aleyah Dawkins

%%JMU-EID: barneyjm, dawkinan

%%DATE: 9/16/13

%
%PROGRAM: JamesAleyah_pyth.m

%
%PURPOSE: Finds a Pythagorean triplet that equals the given number.

%
%VARIABLES: n: number that a, b, and c must equal

%
%JMUPLEDGE

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%a=1<n;

%b=1<n;

%c=1<n;


list[1:n]


% brute-force method for finding Pythagorean Triplets

for a = 1:n

    for b = a+1:n

        for c = b+1:n

            % condsiders if the numbers work as a Pythagorean triplet

            if (a*a)+(b*b)==(c*c)

                if (a+b+c)==n

                    Ans = [a,b,c];

                    return;

                end

            end

        end

    end

end

% To find a Pythagorean triplet more efficiently we could've eliminated the

% for loops 
and replaced them with matrices to solve for multiple variables
% simultaneously. We didn't understand how to implement that in our program.
end
