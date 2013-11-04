function Barney_cobweb(a, x_0, n)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% NAME: James Barney
% JMU-EID: barneyjm
% DATE: 10/7/13
%
% PROGRAM: Barney_cobweb.m
%
% PURPOSE: Plot the FPI and accompanying cobweb diagrams for A*X*(1-X)
%
% VARIABLES: 
%            a: the leading coefficient for the FPI
%            x_0: the initial value for the FPI
%            n: the number of iterations the user wants to compute
%            func: an inline function
%            x : array of length n for storing computated iterations
%            min_x, max_x: useful constants for constructing graphs
%            coordGrid_x: list of 1000 equally spaced values between min_x and max_x
%            x_plot, y_plot: collection of points generated using
%               calculated iterations
%            i, j: iterates to control looping
%
%
% COMMENT:
%   I did extensively look through MathWorks for built-in functions and
%   examples for how to plot points on a graph since that confused me a
%   great deal when I was doing this. 
%
% JMUPLEDGE
%            
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%makes an inline function that I can call later, easily. Found through
%Mathworks. Vectorize turns the string into a mathematical equation.
func = inline(vectorize('a*x*(1-x)'));
x = zeros(1, n);

% computes the values for the FPI using the given function
x(1) = x_0;
for i = 1:n-1
    x(i+1) = a*x(i)*(1-x(i));
end

% useful later for setting up the graphspace
min_x = 0;
max_x = 1;

%makes 1000 values between 0 and 1 allowing for smooth graphs
coordGrid_x = linspace(min_x, max_x, 1000); 

% plots the given function using 'a' and the initial condition.
plot(coordGrid_x, func(a, coordGrid_x), 'b-');
hold on

% plots y = x, basically.
plot(coordGrid_x, coordGrid_x, 'r-');
hold on

%iterates through the n iterations desired. 
for j = 1:n
    % plot the vertical lines
    if j > 1 %this accounts for Matlab's arrays starting at index = 1, not 0. Prevents OutOfBounds Exception
        % stores the values as coordinates for a point
        x_plot = [x(j-1) x(j)];
        y_plot = [x(j) x(j)];
        % plots the stored point. 
        plot(x_plot,y_plot,'r:')
    end
    % Plot the horizontal lines
    if j < n %this accounts for Matlab's arrays starting at index = 1, not 0. Prevents OutOfBounds Exception
        % stores the values as coordinates for a point
        x_plot = [x(j) x(j)];
        y_plot = [x(j) x(j+1)];
        % plots the stored point
        plot(x_plot,y_plot,'b')
    end
end

%plots the first point as a red star, as desired.
plot(x_0, 0, 'r*')
%connects the first point to the first vertical line in the graph
plot([x_0 x(1)], [x_0 0], 'b')

%sets the title using the given a and x_0
title(strcat(num2str(a), '*X*(1-X)', '   X_o= ', num2str(x_0)));

end