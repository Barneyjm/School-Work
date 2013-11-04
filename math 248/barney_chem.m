% James Barney
% barneyjm
% JMUPLEDGE

function [x, y] = barney_chem(x0, y0, t, s)

x_plot = zeros(1,s);
y_plot = zeros(1,s);

x_plot(1) = x0;
y_plot(1) = y0;

for i = 1:s-1

    x_plot(i+1) = x_plot(i) + (x_prime(x_plot(i), y_plot(i)))*t;
    y_plot(i+1) = y_plot(i) + (y_prime(x_plot(i), y_plot(i)))*t;
    
end

plot(x_plot, y_plot)

x = x_plot;
y = y_plot;
end


function f = x_prime(x,y)
%derivative of x
f = y;
end

function f = y_prime(x,y)
%derivative of y
f = 8*(1-x^2)*y-x;
end