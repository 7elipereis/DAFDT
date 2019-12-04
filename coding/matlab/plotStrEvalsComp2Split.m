function [] = plotStrEvalsComp2Split(DS1,DS2,name,filename, legendfirst, legendsecond, xlabeltext,ylabeltext)
    clf;
    set(0,'DefaultFigureVisible','off');
    DS1(DS1 == 0) = NaN;
    DS2(DS2 == 0) = NaN;
    
    
    DS1I = DS1(1:size(DS1,1)/2,:);
    DS1E = DS1((size(DS1,1)/2)+1:end,:);
    DS2I = DS2(1:size(DS2,1)/2,:);
    DS2E = DS2((size(DS2,1)/2)+1:end,:);
    
    
    f = figure;
    p = uipanel('Parent',f,'BorderType','none'); 
    p.Title = name; 
    p.TitlePosition = 'centertop'; 
    p.FontSize = 9;
    p.FontWeight = 'bold';
    
    
    hAxis(1) = subplot(1,2,1, 'Parent', p);
    %h1 = plot(DS1(:,2), 'color', 'b');    
    h1 = scatter(DS1I(:,1),DS1I(:,2),'Marker', '*', 'MarkerEdgeColor', 'b');
    hold on;
    %h2 = plot (DS2(:,2), 'color', 'r');    
    h2 = scatter(DS2I(:,1),DS2I(:,2),'Marker', '+', 'MarkerEdgeColor', 'r');
    hold on;
    
    
    ytickmin = min(min(min(DS1(:,2)),min(DS2(:,2))));
    ytickmax = max(max(max(DS1(:,2)),max(DS2(:,2))));
    
    y = ytickmin:5:100;
    yticks(y);
    ylim([ytickmin-5 110]);
    ylabel(ylabeltext);
    
    listxticks = max(DS1I(:,1),DS2I(:,1));
    listxticks(isnan(listxticks))=0;
    
        
    xlim([(min(max(DS1I(:,1),DS2I(:,1)))-2) (max(max(DS1I(:,1),DS2I(:,1))))]+1);
    xticks(single(listxticks));
    
    title('Initial batches');
    xlh = xlabel(xlabeltext);
    xlh.Position(2) = xlh.Position(2)-0.01;
    
    xtickangle(90);
    
    
    
    
    hAxis(2) = subplot(1,2,2, 'Parent', p);
    %h1 = plot(DS1(:,2), 'color', 'b');    
    h1 = scatter(DS1E(:,1),DS1E(:,2),'Marker', '*', 'MarkerEdgeColor', 'b');
    hold on;
    %h2 = plot (DS2(:,2), 'color', 'r');    
    h2 = scatter(DS2E(:,1),DS2E(:,2),'Marker', '+', 'MarkerEdgeColor', 'r');
    hold on;
    
    title('Final batches');
    
    y = ytickmin:5:100;
    yticks(y);
    ylim([ytickmin-5 110]);
    ylabel('');
    
    
    listxticks = max(DS1E(:,1),DS2E(:,1));
    listxticks(isnan(listxticks))=0;
    
    xticks(single(listxticks));    
    xlim([(min(max(DS1E(:,1),DS2E(:,1)))-1) (max(max(DS1E(:,1),DS2E(:,1)))+1)]);
    
    
    
    
    xlh = xlabel(xlabeltext);
    xlh.Position(2) = xlh.Position(2)-0.01;
    xtickangle(90);   
    h = legend([h1 h2],legendfirst,legendsecond);
    
    
    pos2 = get(hAxis(2), 'Position');
    
    pos2(1) = pos2(1)-0.05;
    
    
    set(hAxis(2), 'Position', pos2);
    
    set(hAxis(2), 'YColor', 'none');
    
    pos1 = get(hAxis(1), 'Position');
    
    pos1(1) = pos1(1)+0.05;
    
    set(hAxis(1), 'Position', pos1);
    
    set(hAxis(2), 'YColor', 'none');
    %set(h,'Location', 'South');
    
    
    print(filename, '-dpng');
    clf;
    
    
    
end

