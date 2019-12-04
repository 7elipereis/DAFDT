function [] = plotOverlappedGradientBoxes(DS,Lims,Weights,Labels,filename,plottitle)
    %avoid showing up windows when called from java
    clf;
    set(0,'DefaultFigureVisible','off');
    
    
    PDS = permute(DS,[3 2 1]);    
    %need to be done to transform columns in lines
    %RD = DS(:,:,1)    
    %RD = RD'
    
    DTA = [0 0 0 0];  
    %LengthDS = size(PDS,3)
    for ri = 1:size(PDS,3)  
        RD = PDS(:,:,ri);
        RD = RD';
        for rdi=1:size(RD,1)
            %this loops every instance in rule data
            RDI = RD(rdi,:);
            nonzero = (RDI(:,1) + RDI(:,2) + RDI(:,3)); 
            if nonzero
                DTAI = horzcat(RDI,1);
                DTA = vertcat(DTA,DTAI);
            end            
        end
    end
    DTA(1,:) = [];
       
    %DTA all data points with alpha Weight    
    
    %loop through all data
    for idta=1:size(DTA,1)
        %test if the data point is inside of any of the boxes and test if
        %it disagrees, in case yes dimminish the alpha from the datapoint        
        for ilims=1:size(Lims,1)
            
            %P = inpolygon(DTA(idta,1), DTA(idta,2), Lims(ilims,:));
            P = inpolygon(DTA(idta,1), DTA(idta,2), [Lims(ilims,1) Lims(ilims,2)],[Lims(ilims,3) Lims(ilims,4)]);
            %test if P is in the polygon
            if P(1,1)
               %test if the data point disagree with the rule;
               if DTA(idta,3) ~= Labels(ilims)
                  DTA(idta,4) = (DTA(idta,4)-0.1);
               end
            end
        end
    end
    %at this point the composition of DTA is
    %DTA(:,1) A values
    %DTA(:,2) B values
    %DTA(:,3) Label/Class values
    %DTA(:,4) Alpha values varying between 1..0 : 1 means there were no
    %disagremment in any box, and for each disagreement it decreases 0.1
    %from this alpha value.
    %DTA ready to be plotted with column number 4 being the alpha
    
    for idta=1:size(DTA,1)
        if DTA(idta,3) == 0
            %plot red
            scatter(DTA(idta,1),DTA(idta,2), 10,  'filled','o', 'MarkerFaceColor', 'red', 'MarkerFaceAlpha', DTA(idta,4));
            hold on;
        end
        if DTA(idta,3) == 1
            %plot blue
            scatter(DTA(idta,1),DTA(idta,2), 10,  'filled','o', 'MarkerFaceColor', 'blue', 'MarkerFaceAlpha', DTA(idta,4));
            hold on;
        end        
    end
    
    hold on;
    xlabel('A');
    ylabel('B');
    title(plottitle);
    xlim([0 100]);
    ylim([0 100])
    xticks(0:10:100)
    yticks(0:10:100)
    
    hold on;
    
    s = size(Lims);
    s = s(1);
    for i=1:s
        amin = Lims(i,1);
        amax = Lims(i,2);
        bmin = Lims(i,3);
        bmax = Lims(i,4);
        x = amin;
        y = bmin;
        w = amax - amin;
        h = bmax - bmin;
        
        %str = { strcat(num2str(amin,'%01.f'),'<=A<=',num2str(amax,'%01.f')),strcat(num2str(bmin,'%01.f'),'<=B<=',num2str(bmax,'%01.f')),L(i,:)};
        
        str = {strcat('w ',num2str(Weights(i)))};
        rect = rectangle('Position',[x y w h]);
        %add background color to rectangle
        if Labels(i) == 0
            set(rect, 'FaceColor', [1 0 0 Weights(i)]);            
        else
            set(rect, 'FaceColor', [0 0 1 Weights(i)]);           
        end      
        
        
        rectpos = get(rect,'Position');
        wreccenter = rectpos(1,3)/2 + rectpos(1,1);
        hreccenter = rectpos(1,4)/2 + rectpos(1,2);
        
        label = text(rectpos(1,1),rectpos(1,2), str,'Color','black','FontSize',6, 'HorizontalAlignment', 'center');
        set(label, 'BackgroundColor', 'yellow');
        
        labelpos = get(label, 'Extent');
        %wlabcenter = labelpos(1,3)/2 + labelpos(1,1);
        %hlabcenter = labelpos(1,4)/2 + labelpos(1,2);
        
        labelcenter = [ (wreccenter) (hreccenter-labelpos(1,4)/5) 0 ];
        set(label, 'Position', labelcenter);
        
        %xl = x;
        %yl = y;
        %xl = (w/2) + xl;
        %yl = (h/2) + yl;
        %label = { strcat(num2str(x), ' <= A <= ', num2str(w)), strcat(num2str(y), ' <= B <= ', num2str(h))};
        %text (xl,yl,label, 'Color', '', 'FontSize',10 );
        
    end
    
    %works to print on file from java
    print(filename, '-dpng');
    %doenst work to print on file from java
    %saveas(fig, "plot.png", "png");
    clf;
    
end

