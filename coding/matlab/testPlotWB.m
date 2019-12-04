function [] = testPlotWB(DS)

    gscatter(DS(:,1),DS(:,2),DS(:,3),['r', 'b'] ,'.',10);
    xlabel('A');
    ylabel('B');
    hold on
    s = size(R);
    s = s(1);
    for i=1:s
        x = R(:,1)
        y = R(:,3)
        w = R(:,2) - x
        h = R(:,4) - y
        
        
        
        %rectangle('Position',[x y w h]);
        
    end
    
end

