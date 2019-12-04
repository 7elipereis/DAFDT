function [ DS ] = generateDS( instances, startA, endA, startB, endB)
%GENERATEDS Summary of this function goes here
%   Detailed explanation goes here
    
    
    Q0 = genrand(instances/4,startA,endA/2,startB,endB/2,1);
    Q1 = genrand(instances/4,endA/2,endA,startB,endB/2,0);
    Q2 = genrand(instances/4,endA/2,endA,endB/2,endB,1);
    Q3 = genrand(instances/4,startA,endA/2,endB/2,endB,0);
    
    DS = vertcat(Q0,Q1,Q2,Q3);
    clear Q0 Q1 Q2 Q3;
    clearvars -global
end

