% James Barney
% barneyjm
% JMUPLEDGE

function z = barney_lucas(l1, l2, n)

z = zeros(1,n);
z(1) = l1;
z(2) = l2;

for i = 2:n-1
    %do lucas calculation
    z(i+1) = z(i-1) + z(i);
end

end