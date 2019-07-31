function [] = plotb_(DS)
%PLOTB_ Summary of this function goes here
%   Detailed explanation goes here
    gsca = gscatter(DS(:,1),DS(:,2),DS(:,3),['r', 'b'] ,'.',10);
    alpha(gsca,.9);
    hold on;
    
    A = [0 0 0];
    B = [0 0 0];
    C = [0 0 0];
    D = [0 0 0];
    E = [0 0 0];
    
    s = size(DS);
    s = s(1);
    %line([0 56.252],[0 37.104],'Color','k');
    %rectangle('Position', [0 0 56.252 37.104])
    %line([56.252 100],[0 37.104],'Color','k');
    %line([0 34.157],[37.104 100],'Color','k');
    %line([34.157 100],[37.104 66.738],'Color','k');
    %line([34.157 100],[66.738 100],'Color','k');
    for i=1:s
        if DS(i,1)>=0 && DS(i,1)<=56.252 && DS(i,2)>=0 && DS(i,2)<=37.104
            A = vertcat(A,[DS(i,1) DS(i,2) DS(i,3)]);
        end
        if DS(i,1)>=56.252 && DS(i,1)<=100 && DS(i,2)>=0 && DS(i,2)<=37.104
            B = vertcat(B,[DS(i,1) DS(i,2) DS(i,3)]);
        end
        if DS(i,1)>=0 && DS(i,1)<=34.157 && DS(i,2)>=37.104 && DS(i,2)<=100
            C = vertcat(C,[DS(i,1) DS(i,2) DS(i,3)]);
        end
        if DS(i,1)>=34.157 && DS(i,1)<=100 && DS(i,2)>=37.104 && DS(i,2)<=66.738
            D = vertcat(D,[DS(i,1) DS(i,2) DS(i,3)]);
        end
        if DS(i,1)>=34.157 && DS(i,1)<=100 && DS(i,2)>=66.738 && DS(i,2)<=100
            E = vertcat(E,[DS(i,1) DS(i,2) DS(i,3)]);
        end
    end
    
    %a = genrand(10000, 0, 56.252, 0, 37.104, 0);
    %x = a(:,1);
    %y = a(:,2);
    %k = boundary(x,y);
    %plot(x(k),y(k));
    hold on;
    
   
    str = {' 0 <= A <=56,252', '0 <= B <= 37.104'};    
    rect = rectangle('Position', [10 50 56.252 37.104]);    
    %set(rect, 'FaceColor', 'magenta');
    
    rectpos = get(rect,'Position')
    wreccenter = rectpos(1,3)/2 + rectpos(1,1);
    hreccenter = rectpos(1,4)/2 + rectpos(1,2);    
    label = text(rectpos(1,1),rectpos(1,2), str,'Color','black','FontSize',6,'HorizontalAlignment', 'center');   
    set(label, 'BackgroundColor', 'yellow');    
    labelpos = get(label, 'Extent');
    wlabcenter = labelpos(1,3)/2 + labelpos(1,1);
    hlabcenter = labelpos(1,4)/2 + labelpos(1,2);
    
    
    
    labelcenter = [ (wreccenter) (hreccenter-labelpos(1,4)/5) 0 ]
    set(label, 'Position', labelcenter);
    
    %text(rectpos(1,3)/2 ,rectpos(1,4)/4, str,'Color','white','FontSize',16);
    
    %rec = rectangle('Position',[10 50 56.252 37.104]);
    %pos = get(rec, 'Position');
    %w = ((pos(1,3)+pos(1,1))/3)/100;
    %h = ((pos(1,4)+pos(1,2))/1)/100;
    %h = h - (h/10);
    %apos = [w h 0 0];
    %pos1 = [pos(1,1),pos(1,2), (pos(1,3)/2)/100, (pos(1,4)/2)/100];
    %w = (pos(1,3)/3)/100; 
    %h = (pos(1,4))/100;
    %pos2 = [w h 0 0];
    %xl = (56.252/2)/100;
    %yl = (37.104/2)/100;
    %ano = annotation('textbox',apos, 'String', str,'FitBoxToText','on', 'BackgroundColor', 'w', 'FaceAlpha', 1);
    %anop = get(ano, 'Position')
    %set(ano, 'Position', [.5 .5 0 0])

   
    
    
    
    axis([0 100 0 100]);
    
    

end

