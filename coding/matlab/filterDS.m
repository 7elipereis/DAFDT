function [ r ] = filterDS( DS, min, max )
%FILTERDS Summary of this function goes here
%   Detailed explanation goes here
    A = [0 0 0];
    
    s = size(DS);
    s = s(1);
    for i=1:s
        if DS(i,1)>=min && DS(i,1)<=max && DS(i,2)>=min && DS(i,2)<=max
            A = vertcat(A,[DS(i,1) DS(i,2) DS(i,3)]);
        end
    end
    r = A([2:end],:);
    %plotDS(r);
    clear A;
    clearvars -global
end

