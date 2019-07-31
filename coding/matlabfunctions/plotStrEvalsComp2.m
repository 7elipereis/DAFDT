function [ ] = plotStrEvalsComp2(DS1,DS2,name,filename, legendfirst, legendsecond, xlabeltext, ylabeltext)
clf;
    set(0,'DefaultFigureVisible','off');
    DS1(DS1 == 0) = NaN;
    DS2(DS2 == 0) = NaN;
    
    %h1 = plot(DS1(:,2), 'color', 'b');    
    h1 = scatter(DS1(:,1),DS1(:,2),'Marker', 'square', 'MarkerEdgeColor', 'g');
    hold on;
    %h2 = plot (DS2(:,2), 'color', 'r');    
    h2 = scatter(DS2(:,1),DS2(:,2),'Marker', 'diamond', 'MarkerEdgeColor', 'm');
    hold on;
    
    
    
    listxticks = max(DS1(:,1),DS2(:,1));
    listxticks(isnan(listxticks))=0;
    
    xticks(single(listxticks));    
    xlim([(min(max(DS1(:,1),DS2(:,1)))-1) (max(max(DS1(:,1),DS2(:,1)))+1)]);
    
    ytickmin = min(min(DS1(:,2)),min(DS2(:,2)));
    %ytickmax = max(max(DS1(:,2)),max(DS2(:,2)));
    
    
    
    
    y = ytickmin:5:100;
    yticks(y);
    ylim([ytickmin-5 110]);
    ylabel(ylabeltext);
    
   
    xlabel(xlabeltext);
    xtickangle(90);
    title(name, 'fontsize', 9);
    h = legend([h1 h2],legendfirst,legendsecond);
    %set(h,'Location', 'South');
    print(filename, '-dpng');
    
    
    clf;
end