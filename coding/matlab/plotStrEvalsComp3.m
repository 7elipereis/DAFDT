function [] = plotStrEvalsComp3(DS1,DS2, DS3, DS4  ,name,filename, legendfirst, legendsecond, legendthird, legendfourth,xlabeltext,ylabeltext)
    clf;
    set(0,'DefaultFigureVisible','off');
    DS1(DS1 == 0) = NaN;
    DS2(DS2 == 0) = NaN;
    DS3(DS3 == 0) = NaN;
    DS4(DS4 == 0) = NaN;
    %h1 = plot(DS1(:,2), 'color', 'b');    
    h1 = scatter(DS1(:,1),DS1(:,2),'Marker', '*', 'MarkerEdgeColor', 'b');
    hold on;
    %h2 = plot (DS2(:,2), 'color', 'r');    
    h2 = scatter(DS2(:,1),DS2(:,2),'Marker', '+', 'MarkerEdgeColor', 'r');
    hold on;
    %h3 = plot (DS3(:,2), 'color', 'g');    
    h3 = scatter(DS3(:,1),DS3(:,2),'Marker', 'square', 'MarkerEdgeColor', 'g');
    hold on;
    %h4 = plot(DS4(:,2), 'color', 'm');    
    %h4 = plot(DS4(:,2), '--', 'MarkerSize', 10, 'Marker', '*', 'Color', 'm');
    h4 = scatter(DS4(:,1),DS4(:,2),'Marker', 'diamond', 'MarkerEdgeColor', 'm');
    
    listxticks = max(DS1(:,1),DS2(:,1));
    listxticks(isnan(listxticks))=0;
    
    xticks(single(listxticks));
    xtickangle(90)
    xlim([(min(max(DS1(:,1),DS2(:,1)))-1) (max(max(DS1(:,1),DS2(:,1)))+1)]);
    
    
        
    ytickmin = min(min(min(DS1(:,2)),min(DS2(:,2))), min(min(DS3(:,2)),min(DS4(:,2))));
    ytickmax = max(max(max(DS1(:,2)),max(DS2(:,2))), max(max(DS3(:,2)),max(DS4(:,2))));
    
    
    
    
    y = ytickmin:5:100;
    yticks(y);
    ylim([ytickmin-5 110]);
    ylabel(ylabeltext);
   
    xlabel(xlabeltext);
    
    title(name, 'fontsize', 9);
    h = legend([h1 h2 h3 h4],legendfirst,legendsecond, legendthird, legendfourth);    
    h.FontSize = 7;
        
    %set(h,'Location', 'South');
    print(filename, '-dpng');
    
    
    clf;
    
end

