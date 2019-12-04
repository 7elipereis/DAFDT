function [generated] = genRot(size,angle)
%GENROT Summary of this function goes here
%   Detailed explanation goes here
    DS = genDS4(size*10,0,100,0,100);
    DSR = rot(DS,angle);
    generated = datasample(DSR,size);
    clear DS DSR;
    clearvars -global
end

