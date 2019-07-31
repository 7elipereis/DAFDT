function [generated] = genRot2DDrift(size,angle,drift)
%GENROT Summary of this function goes here
%   Detailed explanation goes here
    DS = genDS2(size*10,0,100,0,100);    
    DSR = rot(DS,angle);
    generated = datasample(DSR,size);
    generated = [generated(:,1)+drift generated(:,2)+drift generated(:,3)];
    
end

