function [ D ] = genrand( instances, startA, endA, startB, endB, result )
%GENRAND Summary of this function goes here
%   Detailed explanation goes here
    
    A = (endA-startA).*rand(instances,1) + startA;
    B = (endB-startB).*rand(instances,1) + startB;
    if result==0
        C = zeros(instances,1);
    else
        C = ones(instances,1);
    end
    
    D = horzcat(A,B,C);
    clear A B C;
    clearvars -global
end

