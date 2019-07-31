function [  ] = plotDS(DS,filename,plottitle)
%PLOTDS Summary of this function goes here
%   Detailed explanation goes here

    %avoid showing up windows when called from java
    set(0,'DefaultFigureVisible','off');
    gscatter(DS(:,1),DS(:,2),DS(:,3),['r', 'b'] ,'.',10);
    xlabel('A');
    ylabel('B');
    xlim([0 100]);
    ylim([0 100])
    xticks(0:10:100)
    yticks(0:10:100)
    title(plottitle);
    
    
    
    %works to print on file from java
    print(filename, '-dpng');
    clear DS;
    %doenst work to print on file from java
    %saveas(fig, "plot.png", "png");
    clearvars -global
    
    
    
    
end

