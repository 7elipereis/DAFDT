function [] = printPctCorrectStream(data,filename,plottitle)
%PRINTPCTCORRECTSTREAM Summary of this function goes here
%   Detailed explanation goes here
    
    set(0,'DefaultFigureVisible','off');    
    figure1 = figure;
    axes1 = axes('Parent',figure1);
    hold(axes1,'on');
    data = data';
    plot(data);
    ylabel('Pct Correctly classified %');
    xlabel('Stream');
    box(axes1,'on');
    ylim(axes1,[0 100]);
    set(axes1,'YTick',[0 10 20 30 40 50 60 70 80 90 100]);
    title(plottitle);
    print(filename, '-dpng');
end

