function C = barney_linalgops(A, B, operation)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% NAME: James Barney
% JMU-EID: barneyjm
% DATE: 10/27/13
%
% PROGRAM: barney_linalgops.m
%
% PURPOSE: Computes simple addition, subtraction, dot product, and star-dot
% product of the given matrices, A and B.
%
% VARIABLES: C: The resultant matrix of an operation
%            a, b: size of the parent matrices
%
% JMUPLEDGE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

a = size(A); % useful information later
b = size(B);

if operation == '+',
    %addition
    C = add_mats(A,B,1,a,b); %pass a 1 for addition 
elseif operation == '-',
    %subtraction
    C = add_mats(A,B,0,a,b); %pass a 0 for subtraction
   
elseif operation(1,1) == '.',
    %star-dot product
    C = dot_product(A,B,1,a,b); %pass a 1 for star-dot product
elseif operation == '*',
    %regular dot product
    C = dot_product(A,B,0,a,b); %pass a 0 for regular dot product
else
    fprintf('Operation not recognized\n')
    C = zeros(1,1);
    return
end

end

function C = add_mats(A,B, operation, a, b)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Subfunction Adds (or subtracts) two matrices
%
% VARIABLES: A, B: parent matrices to operate on
%            a, b: size of their parent matrices
%            C: output of resultant operation
%            i, j: various counters
%            operation: keeps track of which operation to do
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

if a==b, % ensures a valid calculation
    C = zeros(a(1), a(2)); %preallocates for speed
    if operation == 0, 
        % Subtraction is just negative addition so just make the whole
        % thing negative then add it...
        for i = 1:b(1)
            for j = 1:b(2)
                B(i,j) = -1 * B(i,j);
            end
        end 
    end
        
    %carry on with addition
    for i = 1:b(1)
        for j = 1:b(2)
            C(i,j) = A(i,j) + B(i,j);
        end
    end  
    
else
    C = zeros(1,1); %the program needs to output something...
    fprintf('Cannot add matrices: size A != size B\n');  
    return
end

end

function C = dot_product(A,B, operation, a, b)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Subfunction: Computes the dot product of two matrices
%
% VARIABLES: A, B: parent matrices to operate on
%            a, b: size of their parent matrices
%            C: output of resultant operation
%            i, j: various counters
%            operation: keeps track of which operation to do
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%do dot multiplication
if operation == 0 && a(2) == b(1), %makes sure it's a valid calculation
    C = zeros(a(2),b(1)); %preallocates for speed
    for i = 1:a(1)
        for j = 1:b(2)
            C(i,j) = dot_calc(A(i,:), B(:,j));          
        end
    end
    
elseif (operation == 1) & (a == b), %makes sure it's a valid calculation
    C = zeros(a(1), a(2)); %preallocates for speed
    for i = 1:a(1)
        for j = 1:a(2)
            C(i,j) = A(i,j)*B(i,j);          
        end
    end
    
else
    C = zeros(1,1);
    fprintf('Matrices given are incorrect size for desired operation.\n');
    return
end

end


function result = dot_calc(A,B)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Multiplies two vectors, A and B, together either with dot product
%
% VARIABLES: A, B: parent matrices to operate on
%            n: the length of the first vector
%            result: the returned value
%            i: counter
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

n = length(A);
result = 0;

for i = 1:n
    result = result + A(i)*B(i); 
end

end


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Optimizations: Addition and subtraction are basically the same thing, so
% i combined those.
%
% I computed the size of each matrix only once and just
% passed that around instead of computing it for each operation (although
% with one execution of the program for each pair of matrices, you'd only
% calculate it once anyways.) 
%
% I could have combined the star-dot and regular dot product more but after
% running tests, just having the star-dot do its own thing and NOT doing
% another operation check mid loop was actually faster (on my computer
% anyways). 
