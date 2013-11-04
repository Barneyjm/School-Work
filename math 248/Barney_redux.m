function Barney_redux()
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% NAME: James Barney
% JMU-EID: barneyjm
% DATE: 10/7/13
%
% PROGRAM: Barney_redux.m
%
% PURPOSE: Plot the cobweb diagrams returned from Barney_cobweb.m
%
% VARIABLES: None
%
% JMUPLEDGE
%            
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% a = 2, x_0 = 0.1, n = 50   ONE POINT
subplot(2, 4, 1), Barney_cobweb(2, 0.1, 50);
% a = 3, x_0 = 0.1, n = 50   MULTIPLE ORBITS
subplot(2, 4, 2), Barney_cobweb(3, 0.1, 50);
% a = 4, x_0 = 0.1, n = 50   CHAOS
subplot(2, 4, 3), Barney_cobweb(4, 0.1, 50);
% a = 5, x_0 = 0.1, n = 50   DIVERGENT
subplot(2, 4, 4), Barney_cobweb(5, 0.1, 50)


% a = 2, x_0 = 0.6, n = 50   ONE POINT
subplot(2, 4, 5), Barney_cobweb(2, 0.6, 50);
% a = 3, x_0 = 0.6, n = 50   MULTIPLE ORBITS
subplot(2, 4, 6), Barney_cobweb(3, 0.6, 50);
% a = 5, x_0 = 0.6, n = 50   CHAOS
subplot(2, 4, 7), Barney_cobweb(4, 0.6, 50);
% a = 5, x_0 = 0.6, n = 50   DIVERGENT
subplot(2, 4, 8), Barney_cobweb(5, 0.6, 50);

%%
% This is a visualization of each individual point from HW#3. If I recorded 
% the return values and orbits of each initial condtion and 'a' value, the 
% bifurcation map would arise as show in HW#3. 
%

%%
%
% x_n+1 = a*x_n*(1-x_n)
% x = 2*x*(1-x)
% 1/2 = 1-x
% 1/2 - 1 = -x
% x = 1/2 = Fixed Point
%
% g(x) = 2*x*(1-x)
% g(x) = 2x-2x^2
% g'(x) = 2-4x --> x = 1/2
% g'(1/2) = 2-4(1/2)
% g'(1/2) = 0
% 0 < 1 therefore, FP(1/2) is convergent.
% Therefore, while a = 2, g(x) is convergent on all intervals since there
% is only one fixed point:
% <------0----->
%  > > > . < < < 
%%
end