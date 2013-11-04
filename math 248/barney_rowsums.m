% James Barney
% barneyjm
% JMUPLEDGE

% This program should asks the user to input dimensions of a 2D
% array.  The program should sum the rows of the 2D array and store
% the sums in an array called rowsum.

m=input('Enter number of rows of 2D array\n');
n=input('Enter number of columns of 2D array\n'); 

rand('seed',0);  % this line generates a random seed so that
                 % everyone has the same random 2D array A.
                 % DO NOT EDIT LINE 8

A=round(100*rand(m,n)); 
rowsum=zeros(m,1);      

for j=1:m         
    for i=1:n
        rowsum(j)=rowsum(j)+A(j,i);
    end
end

fprintf('Here is the original array:\n')   
A

fprintf('\n\n Row sums array:\n')
rowsum