function [ds] = BaseLineBatchDrift(size,angle, drift, driftside, noisepercentage)
%BASELINEBATCHDRIFT Summary of this function goes here
%   Detailed explanation goes here

    
    totalinstances = size - (size * (noisepercentage/100));
    totalnoise = (size * (noisepercentage/100));
    
    perclassinstances = totalinstances/2;
    perclassnoise = totalnoise/2;    
    
    if driftside == 0
        drift0 = drift;
        
    end
    if driftside == 1
        drift0 = -drift;
        
    end
    
    
    if angle == 0
        
        q1 = genrand(perclassinstances, 0, 100, 0, 50 + drift0, 0);
        q2 = genrand(perclassinstances, 0, 100,50 + drift0, 100, 1);
        q1noise = genrand(perclassnoise, 0,100,0,100,0);
        q2noise = genrand(perclassnoise, 0,100,0,100,1);

        %ds = vertcat(q1,q1drift,q1noise,q2,q2drift,q2noise);      
        ds = vertcat(q1,q2, q1noise,q2noise);
    end
    if angle == 90
        
        
        q1 = genrand(perclassinstances, 50 + drift0, 100, 0, 100 , 0);
        q2 = genrand(perclassinstances, 0, 50 + drift0, 0, 100, 1);
        q1noise = genrand(perclassnoise, 0,100,0,100,0);
        q2noise = genrand(perclassnoise, 0,100,0,100,1);

        %ds = vertcat(q1,q1drift,q1noise,q2,q2drift,q2noise);      
        ds = vertcat(q1,q2, q1noise,q2noise);
    end
    if angle == 180
        q1 = genrand(perclassinstances, 0, 100, 50 + drift0, 100, 0);
        q2 = genrand(perclassinstances, 0, 100, 0, 50 + drift0, 1);
        q1noise = genrand(perclassnoise, 0,100,0,100,0);
        q2noise = genrand(perclassnoise, 0,100,0,100,1);

        %ds = vertcat(q1,q1drift,q1noise,q2,q2drift,q2noise);      
        ds = vertcat(q1,q2, q1noise,q2noise);
    end
    if angle == 270
        q1 = genrand(perclassinstances, 0, 50 + drift0, 0, 100, 0);
        q2 = genrand(perclassinstances, 50 + drift0, 100, 0, 100, 1);
        q1noise = genrand(perclassnoise, 0,100,0,100,0);
        q2noise = genrand(perclassnoise, 0,100,0,100,1);

        %ds = vertcat(q1,q1drift,q1noise,q2,q2drift,q2noise);      
        ds = vertcat(q1,q2, q1noise,q2noise);
    end
    if angle == 360
        q1 = genrand(perclassinstances, 0, 100, 0, 50 + drift0, 0);
        q2 = genrand(perclassinstances, 0, 100,50 + drift0, 100, 1);
        q1noise = genrand(perclassnoise, 0,100,0,100,0);
        q2noise = genrand(perclassnoise, 0,100,0,100,1);

        %ds = vertcat(q1,q1drift,q1noise,q2,q2drift,q2noise);      
        ds = vertcat(q1,q2, q1noise,q2noise);
    end
    
    
    
end

