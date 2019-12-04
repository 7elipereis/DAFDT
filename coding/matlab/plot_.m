function [  ] = plot_(DS, plottitle)
%PLOTDS Summary of this function goes here
%   Detailed explanation goes here

    %avoid showing up windows when called from java
    set(0,'DefaultFigureVisible','on');
    gscatter(DS(:,1),DS(:,2),DS(:,3),['r', 'b'] ,'.',10);
    hold on;
    %k = boundary (DS(:,1),DS(:,2));
    %plot(k);
    %hold on;
    %hline = refline([0 25]);
    %vline = refline([0 45]);
    %vline1 = refline([])
    %vline.Color = 'k';
    %hline.Color = 'w';
    
    
    %line([10 40], [10 10],'Color','k');
    %line([10 10],[10 40],'Color','k');
    %line([10 40],[10 40],'Color','k');
    %line([10 40],[40 40],'Color','k');
    %line([40 40],[10 40],'Color','k');
    %line([0 56.252],[0 37.104],'Color','k');
    %rectangle('Position', [0 0 56.252 37.104])
    %line([56.252 100],[0 37.104],'Color','k');
    %line([0 34.157],[37.104 100],'Color','k');
    %line([34.157 100],[37.104 66.738],'Color','k');
    %line([34.157 100],[66.738 100],'Color','k');
    
    
    %line([10 10],[10 50], 'Color', 'b');
    xlabel('A');
    ylabel('B');
    xlim([-10 max(DS(:,1))+10]);
    ylim([-10 max(DS(:,2))+10])
    xticks(0:10:max(DS(:,1)))
    yticks(0:10:max(DS(:,2)))
    title(plottitle);
    
    %works to print on file from java
    %print(filename, '-dpng');
    %doenst work to print on file from java
    %saveas(fig, "plot.png", "png");
    
    
end

