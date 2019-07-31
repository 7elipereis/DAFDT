function [rotated] = rot(DS,angle)
%ROT Summary of this function goes here
%   Detailed explanation goes here


    %plotDS(DS);
    DSR = rotateDS(DS,angle);
    DSRN = normDS(DSR,-100,100);    
    DSRNF = filterDS(DSRN,-50,50);    
    DSRNFN = normDS(DSRNF,0,100);
    %plotDS(DSRNFN);
    rotated = DSRNFN;
    
end

