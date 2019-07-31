function [ DS ] = genDS4( instances, startA, endA, startB, endB)
%GENERATEDS Summary of this function goes here
%   Detailed explanation goes here
    ma = (startA + endA)/2;
    mb = (startB + endB)/2;

    Q0 = genrand(instances/4,startA,ma,startB,mb,0);
    Q1 = genrand(instances/4,ma,endA,startB,mb,1);
    Q2 = genrand(instances/4,ma,endA,mb,endB,0);
    Q3 = genrand(instances/4,startA,ma,mb,endB,1);
    
    DS = vertcat(Q0,Q1,Q2,Q3);
    clear Q0 Q1 Q2 Q3;   
    clearvars -global
end