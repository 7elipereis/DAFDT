function [ ] = plotStrEvalsCompBar(DS1,DS2,name,filename, legendfirst, legendsecond)
%PLOTSTREVALSCOMP Summary of this function goes here
%   Detailed explanation goes here
    set(0,'DefaultFigureVisible','off');
    DS1(DS1 == 0) = NaN;
    DS1(DS2 == 0) = NaN;
    b = bar(max(DS1(:,1),DS2(:,1)), [DS1(:,2) DS2(:,2)]);
    b(1).FaceColor = 'blue';
    b(2).FaceColor = 'red';
    %xticks(max(DS1(:,1),DS2(:,1)));
    
    
    % xticklabels(0:nsteps:n);
    % xtickangle(90)
    xlim([(min(max(DS1(:,1),DS2(:,1)))-2) (max(max(DS1(:,1),DS2(:,1)))+2) ]);    
    y = 0:10:100;
    yticks(y);
    ylim([0 120]);
    ylabel('% correctly classified instances');
    xlabel('#batch');
    title(name);
    legend(legendfirst,legendsecond);
    print(filename, '-dpng');
    clf;
    
end

