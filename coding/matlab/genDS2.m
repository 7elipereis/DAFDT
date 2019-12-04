function [ DS ] = genDS2( instances, startA, endA, startB, endB)
%GENERATEDS Summary of this function goes here
%   Detailed explanation goes here
    ma = (startA + endA)/2;
    mb = (startB + endB)/2;

    Q0 = genrand(instances/2,startA,endA,startB,mb,0);
    Q1 = genrand(instances/2,startA,endA,mb,endB,1);
    %Q2 = genrand(instances/4,endA/2,endA,endB/2,endB,1);
    %Q3 = genrand(instances/4,startA,endA/2,endB/2,endB,0);
    
    DS = vertcat(Q0,Q1);
    
end