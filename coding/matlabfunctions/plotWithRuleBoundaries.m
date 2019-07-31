function [] = plotWithRuleBoundaries(DS,R,L,filename,plottitle)
 %avoid showing up windows when called from java
    clf;
    set(0,'DefaultFigureVisible','off');
    
    L = L';
    gscatter(DS(:,1),DS(:,2),DS(:,3),['r', 'b'] ,'.',10);
    xlabel('A');
    ylabel('B');
    title(plottitle);
    xlim([0 100]);
    ylim([0 100])
    xticks(0:10:100)
    yticks(0:10:100)
    
    hold on
    s = size(R);
    s = s(1);
    for i=1:s
        amin = R(i,1);
        amax = R(i,2);
        bmin = R(i,3);
        bmax = R(i,4);
        x = amin;
        y = bmin;
        w = amax - amin;
        h = bmax - bmin;
        
        %str = { strcat(num2str(amin,'%01.f'),'<=A<=',num2str(amax,'%01.f')),strcat(num2str(bmin,'%01.f'),'<=B<=',num2str(bmax,'%01.f')),L(i,:)};
        
        str = {strcat('w ',num2str(L(i,1)))};
        rect = rectangle('Position',[x y w h]);
        rectpos = get(rect,'Position');
        wreccenter = rectpos(1,3)/2 + rectpos(1,1);
        hreccenter = rectpos(1,4)/2 + rectpos(1,2);
        
        label = text(rectpos(1,1),rectpos(1,2), str,'Color','black','FontSize',6, 'HorizontalAlignment', 'center');
        set(label, 'BackgroundColor', 'yellow');
        
        labelpos = get(label, 'Extent');
        %wlabcenter = labelpos(1,3)/2 + labelpos(1,1);
        %hlabcenter = labelpos(1,4)/2 + labelpos(1,2);
        
        labelcenter = [ (wreccenter) (hreccenter-labelpos(1,4)/5) 0 ];
        set(label, 'Position', labelcenter);
        
        %xl = x;
        %yl = y;
        %xl = (w/2) + xl;
        %yl = (h/2) + yl;
        %label = { strcat(num2str(x), ' <= A <= ', num2str(w)), strcat(num2str(y), ' <= B <= ', num2str(h))};
        %text (xl,yl,label, 'Color', '', 'FontSize',10 );
        
    end
    
    %works to print on file from java
    print(filename, '-dpng');
    %doenst work to print on file from java
    %saveas(fig, "plot.png", "png");
    clf;
end

