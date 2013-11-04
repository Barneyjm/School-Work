
clear all;

Last = input('NAME: ','s');

hw8 = [Last,'_linalgops']

%type(hw8)

% GENERAL

A = rand(10,10); B = rand(10,10);

fprintf('Testing A+B for A,B 10x10:\n')
C=feval(hw8,A,B,'+');
fprintf('Error from true value: %d\n\n\n',norm(C-(A+B)))

fprintf('Testing A-B for A,B 10x10:\n')
C=feval(hw8,A,B,'-');
fprintf('Error from true value: %d\n\n\n',norm(C-(A-B)))

fprintf('Testing A.*B for A,B 10x10:\n')
C=feval(hw8,A,B,'.*');
fprintf('Error from true value: %d\n\n\n',norm(C-(A.*B)))

fprintf('Testing A*B for A,B 10x10:\n')
C = feval(hw8,A,B,'*');
fprintf('Error from true value: %d\n\n\n',norm(C-(A*B)))


%WIDE
A=-1*rand(100,500);
B=rand(100,500);

fprintf('Testing A+B for A,B 100x500:\n')
C=feval(hw8,A,B,'+');
fprintf('Error from true value: %d\n\n\n',norm(C-(A+B)))

fprintf('Testing A-B for A,B 100x500:\n')
C=feval(hw8,A,B,'-');
fprintf('Error from true value: %d\n\n\n',norm(C-(A-B)))

fprintf('Testing A.*B for A,B 100x500:\n')
C=feval(hw8,A,B,'.*');
fprintf('Error from true value: %d\n\n\n',norm(C-(A.*B)))

fprintf('Testing A*B for A,B 100x500:\n')
fprintf('(Should get error message to screen)\n')
feval(hw8,A,B,'*');

fprintf('\n\n\n')

%LONG
A=-1*rand(500,100);
B=rand(500,100);

fprintf('Testing A+B for A,B 500x100:\n')
C=feval(hw8,A,B,'+');
fprintf('Error from true value: %d\n\n\n',norm(C-(A+B)))

fprintf('Testing A-B for A,B 500x100:\n')
C=feval(hw8,A,B,'-');
fprintf('Error from true value: %d\n\n\n',norm(C-(A-B)))


fprintf('Testing A.*B for A,B 100x500:\n')
C=feval(hw8,A,B,'.*');
fprintf('Error from true value: %d\n\n\n',norm(C-(A.*B)))

fprintf('Testing A*B for A,B 500x100:\n')
fprintf('(Should get error message to screen)\n')
feval(hw8,A,B,'*');

fprintf('\n\n\n')




%VECTORS
A=rand(7,1);
B=rand(1,7);

fprintf('Testing A+B for A 7x1 and B 1x7\n')
fprintf('(Should get error message to screen)\n')
feval(hw8,A,B,'+');

fprintf('\n\n\n')

fprintf('Testing A-B for A 7x1 and B 1x7\n')
fprintf('(Should get error message to screen)\n')
feval(hw8,A,B,'-');

fprintf('\n\n\n')

fprintf('Testing A.*B for A 7x1 and B 1x7\n')
fprintf('(Should get error message to screen)\n')
feval(hw8,A,B,'.*');

fprintf('\n\n\n')

fprintf('Testing A*B for A 7x1 and B 1x7\n')
fprintf('(Should get C 7x7)\n')
C=feval(hw8,A,B,'*');
fprintf('Error from true value: %d\n\n\n',norm(C-(A*B)))