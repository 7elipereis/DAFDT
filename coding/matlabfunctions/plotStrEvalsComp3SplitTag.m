function [] = plotStrEvalsComp3SplitTag(DS1,DS2, DS3, DS4,name,filename, legendfirst, legendsecond, legendthird, legendfourth,xlabeltext,ylabeltext)
    clf;
    set(0,'DefaultFigureVisible','off');
    DS1(DS1 == 0) = NaN;
    DS2(DS2 == 0) = NaN;
    DS3(DS3 == 0) = NaN;
    DS4(DS4 == 0) = NaN;
    
    DS1I = DS1(1:size(DS1,1)/2,:);
    DS1E = DS1((size(DS1,1)/2)+1:end,:);
    DS2I = DS2(1:size(DS2,1)/2,:);
    DS2E = DS2((size(DS2,1)/2)+1:end,:);
    DS3I = DS3(1:size(DS3,1)/2,:);
    DS3E = DS3((size(DS3,1)/2)+1:end,:);
    DS4I = DS4(1:size(DS4,1)/2,:);
    DS4E = DS4((size(DS4,1)/2)+1:end,:);
    
    
    
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
    %h3 = plot (DS3(:,2), 'color', 'g');    
    h3 = scatter(DS3I(:,1),DS3I(:,2),'Marker', 'square', 'MarkerEdgeColor', 'g');
    hold on;
    %h4 = plot(DS4(:,2), 'color', 'm');    
    %h4 = plot(DS4(:,2), '--', 'MarkerSize', 10, 'Marker', '*', 'Color', 'm');
    h4 = scatter(DS4I(:,1),DS4I(:,2),'Marker', 'diamond', 'MarkerEdgeColor', 'm');
    
    ytickmin = min(min(min(DS1(:,2)),min(DS2(:,2))), min(min(DS3(:,2)),min(DS4(:,2))));
    ytickmax = max(max(max(DS1(:,2)),max(DS2(:,2))), max(max(DS3(:,2)),max(DS4(:,2))));
    
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
    
    loopsize = length(DS1I);
    for c = 1:loopsize
        text(DS1I(c,1), DS1I(c,2),int2str(DS1I(c,3)), 'FontSize', 9);
    end    
    loopsize = length(DS2I);
    for c = 1:loopsize
        text(DS2I(c,1), DS2I(c,2), int2str(DS2I(c,3)),  'FontSize', 9);
    end
    loopsize = length(DS3I);
    for c = 1:loopsize
        text(DS3I(c,1), DS3I(c,2), int2str(DS3I(c,3)),  'FontSize', 9);
    end
    loopsize = length(DS4I);
    for c = 1:loopsize
        text(DS4I(c,1), DS4I(c,2), int2str(DS4I(c,3)),  'FontSize', 9);
    end
    
    
    
    
    hAxis(2) = subplot(1,2,2, 'Parent', p);
    %h1 = plot(DS1(:,2), 'color', 'b');    
    h1 = scatter(DS1E(:,1),DS1E(:,2),'Marker', '*', 'MarkerEdgeColor', 'b');
    hold on;
    %h2 = plot (DS2(:,2), 'color', 'r');    
    h2 = scatter(DS2E(:,1),DS2E(:,2),'Marker', '+', 'MarkerEdgeColor', 'r');
    hold on;
    %h3 = plot (DS3(:,2), 'color', 'g');    
    h3 = scatter(DS3E(:,1),DS3E(:,2),'Marker', 'square', 'MarkerEdgeColor', 'g');
    hold on;
    %h4 = plot(DS4(:,2), 'color', 'm');    
    %h4 = plot(DS4(:,2), '--', 'MarkerSize', 10, 'Marker', '*', 'Color', 'm');
    h4 = scatter(DS4E(:,1),DS4E(:,2),'Marker', 'diamond', 'MarkerEdgeColor', 'm');
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
    
    loopsize = length(DS1E);
    for c = 1:loopsize
        text(DS1E(c,1), DS1E(c,2), int2str(DS1E(c,3)), 'FontSize', 9);
    end    
    loopsize = length(DS2E);
    for c = 1:loopsize
        text(DS2E(c,1), DS2E(c,2), int2str(DS2E(c,3)), 'FontSize', 9);
    end
    loopsize = length(DS3E);
    for c = 1:loopsize
        text(DS3E(c,1), DS3E(c,2), int2str(DS3E(c,3)), 'FontSize', 9);
    end
    loopsize = length(DS4E);
    for c = 1:loopsize
        text(DS4E(c,1), DS4E(c,2), int2str(DS4E(c,3)), 'FontSize', 9);
    end
    
    h = legend([h1 h2 h3 h4],legendfirst,legendsecond, legendthird, legendfourth);
    
    
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

