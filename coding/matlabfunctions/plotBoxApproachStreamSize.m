function [] = plotBoxApproachStreamSize(ds,batchsizes,ymin,ymax, approachname, ylabeltext,xlabeltext)
    %set(0,'DefaultFigureVisible','off');
    %clf;
    boxplot(ds,batchsizes);
    
    ytickmin = floor(ymin);
    ytickmax = ceil(ymax);
    
    ylim([ytickmin-0.2 ytickmax+0.2]);
    yticks(ytickmin:5:ytickmax);
    ax = gca;
    ax.FontSize = 6;
    
    %xticks(batchsizes)
    
    
    title(approachname);
    
    ylabel(ylabeltext);
    xlabel(xlabeltext);
    xtickangle(90);
    
    %clf;
end