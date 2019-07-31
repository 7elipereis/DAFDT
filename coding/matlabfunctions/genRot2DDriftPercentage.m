function [generated] = genRot2DDriftPercentage(size,angle,driftshift, driftpercentage)
%GENROT Summary of this function goes here
%   Detailed explanation goes here
    %DS = genDS2(size*10,0,100,0,100);
    DS = genDS2Drift(driftshift, driftpercentage,size,0,100,0,100);
    DSR = rot(DS,angle);
    generated = DSR;
    %generated = datasample(DSR,size);
    %generated = [generated(:,1)+driftshift generated(:,2)+driftshift generated(:,3)];
    
end

