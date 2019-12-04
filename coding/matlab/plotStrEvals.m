function [] = plotStrEvals(DS, legendtext, name, filename)
%PLOTSTREVALS Summary of this function goes here
%   Detailed explanation goes here
    clear h1;
    set(0,'DefaultFigureVisible','off');
    h1 = plot(DS(:,2), 'color', 'b');
    xticks(DS(:,1));
    y = 0:10:100;
    yticks(y);
    ylim([0 100]);
    ylabel('% correctly classified instances');
    xlabel('# stream or batch');
    title(name);
    legend(h1, legendtext);
    print(filename, '-dpng');
   
end

