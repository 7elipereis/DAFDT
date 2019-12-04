function normalized = normDS(ds, x, y)

     a = ds(:,1);
     b = ds(:,2);
     c = ds(:,3);
     
     %a
     % Normalize to [0, 1]:
     m = min(a);
     range = max(a) - m;
     a = (a - m) / range;

     % Then scale to [x,y]:
     range2 = y - x;
     an = (a*range2) + x;
     
     %b
     % Normalize to [0, 1]:
     m = min(b);
     range = max(b) - m;
     b = (b - m) / range;

     % Then scale to [x,y]:
     range2 = y - x;
     bn = (b*range2) + x;
     
     
     normalized = [an bn c];
     clear an bn c a b c;
     clearvars -global
     
    %plotDS(normalized)
end

