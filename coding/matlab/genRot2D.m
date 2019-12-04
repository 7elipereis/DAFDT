function [generated] = genRot2D(size,angle)
%GENROT Summary of this function goes here
%   Detailed explanation goes here
    DS = genDS2(size*10,0,100,0,100);
    DSR = rot(DS,angle);
    generated = datasample(DSR,size);
    
end

